package fivestar.kTour.service;

import fivestar.kTour.Dto.LoginResponseDto;
import fivestar.kTour.Dto.OauthTokenResponseDto;
import fivestar.kTour.domain.User;
import fivestar.kTour.oauth.KakaoOauthLoginParam;
import fivestar.kTour.oauth.KakaoUserInfo;
import fivestar.kTour.oauth.Oauth2UserInfo;
import fivestar.kTour.repository.UserRepository;
import fivestar.kTour.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthService implements OauthService<KakaoOauthLoginParam> {

    private final UserRepository userRepository;
    private final InMemoryClientRegistrationRepository inMemoryRepository;
    private final TokenService tokenService;

    /**
     * @InMemoryRepository application-oauth properties 정보를 담고 있음
     * @getToken() 넘겨받은 code 로 Oauth 서버에 Token 요청
     * @getUserProfile 첫 로그인 시 회원 가입
     * 유저 인증 후 Jwt AccessToken 생성
     */

    @Transactional
    @Override
    public LoginResponseDto login(KakaoOauthLoginParam params) {

        ClientRegistration provider = inMemoryRepository.findByRegistrationId(params.provider());
        log.info("redirect uri = {}", provider.getRedirectUri());
        OauthTokenResponseDto tokenResponse = getToken(params.code(), provider);
        log.info("OauthAccessToken = {}", tokenResponse.access_token());
        User user = getUserProfile(params.provider(), tokenResponse, provider);

        String accessToken = tokenService.generateToken(user.getUserEmail());
        log.info("ServerAccessToken={}", accessToken);
        return new LoginResponseDto(user.getUserEmail(), "BEARER_TYPE", accessToken);
    }

    private OauthTokenResponseDto getToken(String code, ClientRegistration provider) {
        return WebClient.create()
                .post()
                .uri(provider.getProviderDetails().getTokenUri())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(requestToken(code, provider))
                .retrieve()
                .bodyToMono(OauthTokenResponseDto.class)
                .block();
    }

    private MultiValueMap<String, String> requestToken(String code, ClientRegistration provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("client_id", provider.getClientId());
        return formData;
    }

    private User getUserProfile(String providerName, OauthTokenResponseDto tokenResponse, ClientRegistration provider) {
        Map<String, Object> userAttributes = getUserAttribute(provider, tokenResponse);
        if (!providerName.equals("kakao")) {
            throw new RuntimeException("invalid provider name");
        }

        Oauth2UserInfo oauth2UserInfo = new KakaoUserInfo(userAttributes);
        if (oauth2UserInfo.getProvider() == null) {
            throw new RuntimeException("provider is null");
        }

        String email = oauth2UserInfo.getEmail();
        String nickname = oauth2UserInfo.getNickName();
        String imageUrl = oauth2UserInfo.getImageUrl();
        String ageRange = oauth2UserInfo.getAgeRange();
        String oauth2Provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();

        log.info("ageRange={}", ageRange);
        log.info("imageUrl={}", imageUrl);

        Optional<User> optionalUser = userRepository.findById(email);

        if (optionalUser.isEmpty()) {
            User user = new User(email, nickname, imageUrl, ageRange, oauth2Provider, providerId);
            userRepository.save(user);
            return user;
        } else {
            return optionalUser.get();
        }
    }

    private Map<String, Object> getUserAttribute(ClientRegistration provider, OauthTokenResponseDto tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
                .headers(header -> header.setBearerAuth(String.valueOf(tokenResponse.access_token())))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }
}
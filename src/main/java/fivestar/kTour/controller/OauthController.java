package fivestar.kTour.controller;

import fivestar.kTour.Dto.LoginResponseDto;
import fivestar.kTour.oauth.KakaoOauthLoginParam;
import fivestar.kTour.oauth.NaverOauthLoginParam;
import fivestar.kTour.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OauthController {

    private final OauthService<KakaoOauthLoginParam> kakaoOauthService;
    private final OauthService<NaverOauthLoginParam> naverOauthService;

    /**
     * OAuth 로그인 시 인증 코드를 넘겨받은 후 첫 로그인 시 회원 가입
     */
    @GetMapping("/oauth/{provider}")
    public ResponseEntity<LoginResponseDto> oauthCallback(@PathVariable String provider,
                                                          @RequestParam String code,
                                                          @RequestParam(required = false) String state) {
        log.info("code={}", code);
        if(provider.equals("kakao")) {
            LoginResponseDto res = kakaoOauthService.login(new KakaoOauthLoginParam(provider, code));
            return ResponseEntity.ok().body(res);
        }
        if(provider.equals("naver")) {
            LoginResponseDto res = naverOauthService.login(new NaverOauthLoginParam(provider, code, state));
            return ResponseEntity.ok().body(res);
        }
        throw new IllegalArgumentException("oauth2 provider must be naver or kakao");
    }
}
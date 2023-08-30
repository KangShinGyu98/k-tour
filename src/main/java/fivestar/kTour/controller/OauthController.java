package fivestar.kTour.controller;

import fivestar.kTour.Dto.LoginResponseDto;
import fivestar.kTour.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OauthController {

    private final OauthService kakaoOauthService;

    /**
     * OAuth 로그인 시 인증 코드를 넘겨받은 후 첫 로그인 시 회원 가입
     */
    @GetMapping("/oauth/{provider}")
    public ResponseEntity<LoginResponseDto> kakaoCallback(@PathVariable String provider, @RequestParam String code) {
        log.info("code={}", code);
        LoginResponseDto res = kakaoOauthService.login(provider, code);
        return ResponseEntity.ok().body(res);
    }
}

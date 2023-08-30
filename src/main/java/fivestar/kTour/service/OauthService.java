package fivestar.kTour.service;

import fivestar.kTour.Dto.LoginResponseDto;

public interface OauthService {
    LoginResponseDto login(String providerName, String code);
}

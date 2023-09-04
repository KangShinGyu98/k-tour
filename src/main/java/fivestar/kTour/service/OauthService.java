package fivestar.kTour.service;

import fivestar.kTour.Dto.LoginResponseDto;

public interface OauthService<T> {
    LoginResponseDto login(T params);
}

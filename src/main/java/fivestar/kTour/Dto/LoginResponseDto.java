package fivestar.kTour.Dto;

public record LoginResponseDto(
        String email,
        String tokenType,
        String accessToken) {
}

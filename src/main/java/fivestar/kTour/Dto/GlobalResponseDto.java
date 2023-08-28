package fivestar.kTour.Dto;


import jakarta.validation.constraints.NotBlank;

public record GlobalResponseDto (@NotBlank boolean success){
}

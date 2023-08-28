package fivestar.kTour.Dto;


import jakarta.validation.constraints.NotBlank;

public record GetMyPlansDto (@NotBlank String userEmail){
}


package fivestar.kTour.Dto;


import javax.validation.constraints.NotBlank;

public record GetMyPlansDto (@NotBlank String userEmail){
}


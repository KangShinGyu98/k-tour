package fivestar.kTour.Dto;

import fivestar.kTour.domain.Place;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GetMyPlansResDto (
        @NotBlank Long planId,
        @NotBlank String planName,
//        @NotBlank
        List<Place> places
){
}

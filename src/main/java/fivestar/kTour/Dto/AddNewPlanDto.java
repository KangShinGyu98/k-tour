package fivestar.kTour.Dto;


import fivestar.kTour.domain.Place;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AddNewPlanDto (
        @NotBlank
        String userEmail,
        @NotBlank
        String planName,
        String planNote,
//        @NotBlank
        List<Place> places
){
}

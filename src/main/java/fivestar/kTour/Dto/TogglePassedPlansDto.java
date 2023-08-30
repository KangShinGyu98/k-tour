package fivestar.kTour.Dto;


import fivestar.kTour.domain.Plan;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record TogglePassedPlansDto (
        @NotBlank
        String userEmail,
//        @NotBlank
        List<Plan> plans
) {
}

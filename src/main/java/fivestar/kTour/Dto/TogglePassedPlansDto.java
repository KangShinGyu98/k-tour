package fivestar.kTour.Dto;


import fivestar.kTour.domain.Plan;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TogglePassedPlansDto (
        @NotBlank
        String userEmail,
        List<Plan> plans
) {
}

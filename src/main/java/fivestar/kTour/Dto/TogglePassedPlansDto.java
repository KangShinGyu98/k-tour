package fivestar.kTour.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TogglePassedPlansDto {
    @NotBlank
    private String userEmail;

    private List<PlanIdPassedPair> planIdPassedPairs;
    @Data
    public static class PlanIdPassedPair{
        @NotBlank
        private Long planId;
        @NotBlank
        private boolean passed;
    }
}
//{"userEmail": "test@test",
//    "planIdPassedPairs": [
//        {
//        "planId": "1",
//        "passed": true},
//        {
//        "planId": "2",
//        "passed": false}
//    ]
//}
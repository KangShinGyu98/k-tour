package fivestar.kTour.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TogglePassedPlansDto {
    private String userEmail;
    private List<PlanIdPassedPair> planIdPassedPairs;
    @Data
    public static class PlanIdPassedPair{
        private Long planId;
        private boolean passed;
    }
}
//{"userEmail": "test@test",
//        "planIdPassedPairs": [
//        {
//        "planId": "1",
//        "passed": true
//        },
//        {
//        "planId": "2",
//        "passed": false
//        }
//        ]
//        }
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
    @Getter
    public class PlanIdPassedPair{
        private Long planId;
        private boolean passed;
    }
}

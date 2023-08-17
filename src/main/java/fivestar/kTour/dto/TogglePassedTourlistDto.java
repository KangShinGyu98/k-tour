package fivestar.kTour.dto;

import fivestar.kTour.domain.Tourlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TogglePassedTourlistDto {
    private String userEmail;
    private Long tourlistId;
    private Boolean passed;
}

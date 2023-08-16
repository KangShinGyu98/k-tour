package fivestar.kTour.dto;

import fivestar.kTour.domain.Tourlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTourlistDto {
    private Tourlist tourlist;
}

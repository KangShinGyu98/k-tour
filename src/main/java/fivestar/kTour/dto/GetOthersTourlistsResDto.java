package fivestar.kTour.dto;

import fivestar.kTour.domain.Tourlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOthersTourlistsResDto {
    private List<Tourlist> othersTourlists;
}

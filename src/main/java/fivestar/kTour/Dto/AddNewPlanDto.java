package fivestar.kTour.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewPlanDto {
    private String userEmail;
    private List<String> places;//List<String>
}

package fivestar.kTour.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewPlanDto {
    @NotBlank
    private String userEmail;
    private List<String> places;//List<String>
}

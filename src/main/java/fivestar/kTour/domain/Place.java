package fivestar.kTour.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Place")
@Getter
@Builder
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    @NotBlank
    private String placeName;
    private String placeNote;
    private Boolean passed;
    private Double xPos;
    private Double yPos;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

}


package fivestar.kTour.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}


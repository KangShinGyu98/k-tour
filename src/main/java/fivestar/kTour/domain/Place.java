package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@Table(name = "Place")
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String placeName;
    private String placeNote;
    private boolean passed;
    private double xPos;
    private double yPos;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}


package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "TourList")
@Data
public class TourList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listID;
    private String tourList;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;


}

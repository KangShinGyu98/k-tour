package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@Table(name = "Plan")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private String planName;
    private String planNote;
    private boolean passed;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
}

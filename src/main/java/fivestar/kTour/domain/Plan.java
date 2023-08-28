package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Boolean passed;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    public Plan(String planName,String planNote,User user){
        this.planName = planName;
        this.user = user;
        this.planNote = planNote;
        this.passed = false;
    }
}

package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Tourlist")
@Data
public class Tourlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listID;
    private String Tourlist;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;


}

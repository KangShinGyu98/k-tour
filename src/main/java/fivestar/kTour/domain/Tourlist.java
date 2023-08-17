package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "Tourlist")
@Data
public class Tourlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listID;
    private String tourlist; //
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;


}

package fivestar.kTour.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "User")
@Data
public class User {
    @Id
    @Column(name = "user_email")
    private String userEmail;
    private String userNickname;
    private String userAgeRange;
    private String userPicture;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}




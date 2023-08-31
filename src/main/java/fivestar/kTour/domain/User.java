package fivestar.kTour.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "`User`")
public class User {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    private String nickname;

    private String snsType;

    private String snsId;

    private String imageUrl;

    private String role;

    private String ageRange;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public User(String email, String nickname, String imageUrl, String ageRange, String snsType, String snsId) {
        this.userEmail = email;
        this.nickname = nickname;
        this.snsType = snsType;
        this.snsId = snsId;
        this.imageUrl = imageUrl;
        this.ageRange = ageRange;
        this.role = "ROLE_USER";
        this.createAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
}




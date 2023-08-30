package fivestar.kTour.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "`User`", indexes = {
        @Index(name = "idx_user_email_idx", columnList = "user_email")})
public class User {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    private String role;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public User(String email, String nickname, String imageUrl, Integer ageRange, String provider, String providerId) {
        this.userEmail = email;
        this.role = "ROLE_USER";
        this.userProfile = new UserProfile(this, nickname, imageUrl, ageRange, provider, providerId);
    }
}




package fivestar.kTour.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String imageUrl;

    private Integer ageRange;

    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
    private User user;

    private String provider;

    private String providerId;

    public UserProfile(User user, String nickname, String imageUrl, Integer ageRange, String provider, String providerId) {
        this.user = user;
        this.ageRange = ageRange;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
    }
}
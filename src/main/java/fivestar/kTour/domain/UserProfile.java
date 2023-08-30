package fivestar.kTour.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
    private User user;

    private String provider;

    private String providerId;

    public UserProfile(User user, String provider, String providerId) {
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
    }
}
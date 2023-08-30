package fivestar.kTour.oauth;

public interface Oauth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getNickName();
    String getImageUrl();
    Integer getAgeRange();
}

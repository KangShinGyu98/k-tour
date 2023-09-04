package fivestar.kTour.oauth;

import java.util.Map;

public class NaverUserInfo implements Oauth2UserInfo {

    private final Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(getResponse().get("id"));
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) getResponse().get("email");
    }

    @Override
    public String getNickName() {
        return (String) getResponse().get("nickname");
    }

    @Override
    public String getImageUrl() {
        return (String) getResponse().get("profile_image");
    }

    @Override
    public String getAgeRange() {
        return (String) getResponse().get("age");
    }

    public Map<String, Object> getResponse() {
        return (Map<String, Object>) attributes.get("response");
    }
}

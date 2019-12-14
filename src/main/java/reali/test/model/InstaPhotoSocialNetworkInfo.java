package reali.test.model;

public class InstaPhotoSocialNetworkInfo implements SocialNetworkInfo {
    @Override
    public String getName() {
        return "InstaPhoto";
    }

    @Override
    public String getUrl() {
        return "https://reali-social.herokuapp.com/instaphoto";
    }
}

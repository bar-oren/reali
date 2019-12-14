package reali.test.model;

public class TweetySocialNetworkInfo implements SocialNetworkInfo {
    @Override
    public String getName() {
        return "Tweety";
    }

    @Override
    public String getUrl() {
        return "https://reali-social.herokuapp.com/tweety";
    }
}

package reali.test.logic;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import reali.test.model.BookOfFacesSocialNetworkInfo;
import reali.test.model.InstaPhotoSocialNetworkInfo;
import reali.test.model.SocialNetworkInfo;
import reali.test.model.TweetySocialNetworkInfo;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RetrieveSocialNetworkDataCmd implements Observer{
    private static final SocialNetworkInfo[] SOCIAL_NETWORK_INFOS = {
            new TweetySocialNetworkInfo(),
            new BookOfFacesSocialNetworkInfo(),
            new InstaPhotoSocialNetworkInfo()
    };
    private ConcurrentHashMap<String, String> data = new ConcurrentHashMap<>();

    public Map<String, String> execute() {
        for (SocialNetworkInfo socialNetworkInfo : SOCIAL_NETWORK_INFOS) {
            CompletableFuture.runAsync(new SocialNetworkDataRetrieverRunnable(this, socialNetworkInfo));
        }

        System.out.println("Data is loading...");
        while (data.size() < SOCIAL_NETWORK_INFOS.length) {
        }

        return data;
    }

    @Override
    public void updateData(String socialNetworkHeader, JSONAware data) {
        this.data.put(socialNetworkHeader, data == null ? new JSONObject().toJSONString() : data.toJSONString());
    }
}

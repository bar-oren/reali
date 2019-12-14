package reali.test.logic;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import reali.test.model.SocialNetworkInfo;

import java.io.IOException;

public class SocialNetworkDataRetrieverRunnable implements Runnable {
    private SocialNetworkInfo socialNetworkInfo;
    private Observer observer;

    public SocialNetworkDataRetrieverRunnable(Observer observer, SocialNetworkInfo socialNetworkInfo) {
        this.socialNetworkInfo = socialNetworkInfo;
        this.observer = observer;
    }

    @Override
    public void run() {
        if (socialNetworkInfo == null) {
            throw new IllegalStateException("Could not retrieve social network data without its info");
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(socialNetworkInfo.getUrl()).build();

        boolean succeed = false;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            JSONAware result = (JSONAware) new JSONParser().parse(body.string());
            observer.updateData(socialNetworkInfo.getName(), result);
            succeed = true;
        } catch (IOException e) {
            System.out.println("Social Network data request failed: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Failed to parse Social Network response: " +  e.getMessage());
        } finally {
            if (!succeed) {
                observer.updateData(socialNetworkInfo.getName(), null);
            }
        }

    }

}

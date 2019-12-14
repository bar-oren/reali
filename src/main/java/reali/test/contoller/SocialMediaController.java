package reali.test.contoller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reali.test.logic.RetrieveSocialNetworkDataCmd;

import java.awt.*;
import java.util.Map;

@RestController
public class SocialMediaController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getSocialNetworkData() {
        return new RetrieveSocialNetworkDataCmd().execute();
    }
}

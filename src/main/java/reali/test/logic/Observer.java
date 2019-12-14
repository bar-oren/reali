package reali.test.logic;

import org.json.simple.JSONAware;

public interface Observer {
    void updateData(String socialNetworkHeader, JSONAware data);
}

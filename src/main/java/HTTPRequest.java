
import java.util.HashMap;

public class HTTPRequest {

    private String StartLineImplementation;
    private String StartLineURL;
    private String StartLineStatus;

    private HashMap<String, String> headers = new HashMap<String, String>();


    public void setStartLineStatus(String startLineStatus) {
        StartLineStatus = startLineStatus;
    }


    public HashMap<String, String> getHeaders() {
        return headers;
    }


    public void setStartLineImplementation(String startLineImplementation) {
        StartLineImplementation = startLineImplementation;
    }

    public void setStartLineURL(String startLineURL) {
        StartLineURL = startLineURL;
    }

}
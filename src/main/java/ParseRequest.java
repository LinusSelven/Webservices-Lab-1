import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ParseRequest {

    public JSONObject parse(HTTPRequest httpRequest, BufferedReader in) throws IOException {
        System.out.println("Parsing ---------------------");
        // Get starline
        String[] splitHead = in.readLine().split(" ");
        httpRequest.setStartLineImplementation(splitHead[0].toUpperCase());
        httpRequest.setStartLineURL(splitHead[0]);
        httpRequest.setStartLineStatus(splitHead[1]);

        // Get headers
        String headerLine  = " ";
        while (!headerLine.isEmpty()) {
            headerLine = in.readLine();
            String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
                httpRequest.getHeaders().put(splitHeader[0], splitHeader[1]);
        }

        // Get POST body

            if(httpRequest.getHeaders().containsValue(" application/x-www-form-urlencoded")){
                int contentLength = Integer.parseInt((httpRequest.getHeaders().get("Content-Length")).replace(" ", ""));
                char[] sizeByContentLenght = new char[contentLength];
                in.read(sizeByContentLenght, 0 ,contentLength);
                String jsonBody = new String(sizeByContentLenght);  try {
                    Pattern regex = Pattern.compile("(^|\\s+)name=(.*?)&age=(.*?)(\\s+|$)");
                    Matcher regexMatcher = regex.matcher(jsonBody);

                    if(regexMatcher.matches()){

                        final String nameValue = regexMatcher.group(2);
                        final String ageValue = regexMatcher.group(3);




                        String jsonString = "{\"name\": \""+nameValue+"\", \"age\": \""+ageValue+"\"}";
                        return new JSONObject(jsonString);
                    }

                } catch (PatternSyntaxException ex) {
                    //TODO: Handle it.
                }
               return null;

            }
            return null;
    }
}
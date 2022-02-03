package tech.nevets.deepj.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonExtractor {
    public JsonExtractor() {
    }

    public String extractMessage(String jsonResponse) {
        String message = "Error Processing Request";

        JSONObject jo = new JSONObject(jsonResponse);
        JSONArray ja = jo.getJSONArray("translations");

        for (int i = 0; i < ja.length(); i++) {
            JSONObject joo = ja.getJSONObject(i);
            message = joo.getString("text");
        }

        return message;
    }

    public String extractDetectedLang(String jsonResponse) {
        String lang = "Error Processing Request";

        JSONObject jo = new JSONObject(jsonResponse);
        JSONArray ja = jo.getJSONArray("translations");

        for (int i = 0; i < ja.length(); i++) {
            JSONObject joo = ja.getJSONObject(i);
            lang = joo.getString("detected_source_language");
        }

        return lang;
    }
}

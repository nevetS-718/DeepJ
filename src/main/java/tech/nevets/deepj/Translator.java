package tech.nevets.deepj;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Translator {
    private final String authKey;
    Request r = new Request();

    public Translator(String authKey) {
        this.authKey = authKey;
    }
    public String translate(Enum<Languages> lang, String sourceMessage) {
        String response = "Error processing request";
        try {
            response = r.get(authKey, lang, sourceMessage);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error processing request");
            e.printStackTrace();
        }

        String message = "Error Processing Request";

        JSONObject jo = new JSONObject(response);
        JSONArray ja = jo.getJSONArray("translations");

        for (int i = 0; i < ja.length(); i++) {
            JSONObject joo = ja.getJSONObject(i);
            message = joo.getString("text");
        }

        return message;
    }
}

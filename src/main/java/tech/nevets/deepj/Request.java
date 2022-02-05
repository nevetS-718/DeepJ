package tech.nevets.deepj;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Request {
    public Request() {
    }

    public String get(String authKey, Enum<Languages> langEnum, String message) throws IOException, InterruptedException {
        String encodedAuthKey = URLEncoder.encode(authKey, StandardCharsets.UTF_8);

        String lang;
        lang = langEnum.toString();

        if (langEnum == Languages.ENUS) {
            lang = "EN-US";
        } else if (langEnum == Languages.ENGB) {
            lang = "EN-GB";
        } else if (langEnum == Languages.PTBR) {
            lang = "PT-BR";
        } else if (langEnum == Languages.PTPT) {
            lang = "PT-PT";
        }

        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept","*/*")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("https://api-free.deepl.com/v2/translate?auth_key=" + encodedAuthKey + "&target_lang=" + lang + "&text=" + encodedMessage))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String data = response.body();

        return data;
    }
}

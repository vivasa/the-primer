package the.primer.gems;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Translator {

    private static final String API_KEY = "8a3a2122bamshd3241f9f0833b52p17f94ejsn79a5d52dbada";
    private static final String HOST = "google-translate1.p.rapidapi.com";
    private static final String TRANSLATE_ENDPOINT = "https://google-translate1.p.rapidapi.com/language/translate/v2";
    private static final String DETECT_ENDPOINT = "https://google-translate1.p.rapidapi.com/language/translate/v2/detect";
    private static final OkHttpClient client = new OkHttpClient();

    private static Response makeRequest(String url, String method, RequestBody body) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (method.equals("POST")) {
            requestBuilder.post(body);
        } else if (method.equals("PUT")) {
            requestBuilder.put(body);
        } else if (method.equals("DELETE")) {
            requestBuilder.delete(body);
        } else {
            // Default to GET
            requestBuilder.get();
        }

        Request request = requestBuilder.addHeader("content-type", "application/x-www-form-urlencoded").addHeader("Accept-Encoding", "application/gzip").addHeader("X-RapidAPI-Key", API_KEY).addHeader("X-RapidAPI-Host", HOST).build();

        return client.newCall(request).execute();
    }


    public static void translate(String input, String sourceLanguage, String targetLanguage) throws IOException {
        RequestBody body = new FormBody.Builder().add("q", input).add("target", targetLanguage).add("source", sourceLanguage).build();

        Response response = makeRequest(TRANSLATE_ENDPOINT, "POST", body);
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        Gson gson = new Gson();
        Map result = gson.fromJson(response.body().string(), Map.class);
        System.out.println("Result: " + result);
    }

    public static String detect(String input) throws IOException {
        RequestBody body = new FormBody.Builder().add("q", input).build();

        Response response = makeRequest(DETECT_ENDPOINT, "POST", body);
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        Gson gson = new Gson();
        Map result = gson.fromJson(response.body().string(), Map.class);
        String language = "";
        if (result != null && result.get("data") != null) {
            Map data = (Map) result.get("data");
            List detections = (List) data.get("detections");

            language = (String) ((Map) ((List) detections.get(0)).get(0)).get("language");
        }
        System.out.println("Detected language is " + language);
        return language;
    }

    public static void main(String[] args) {
        try {
            String text = "योगक्षेमं वहाम्यहम्";
            String sourceLanguage = "sa";
            String targetLanguage = "en";
            String detectedLanguage = detect(text);
            System.out.println("Detected language: " + detectedLanguage);
            translate(text, sourceLanguage, targetLanguage);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
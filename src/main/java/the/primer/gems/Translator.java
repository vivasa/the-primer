package the.primer.gems;

import java.util.Map;
import java.util.List;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.FormBody;
import okhttp3.Response;

public class Translator {

  static void translate() {
    try {
      OkHttpClient client = new OkHttpClient();

      RequestBody body = new FormBody.Builder()
          .add("q", "Hello, world!")
          .add("target", "te")
          .add("source", "en")
          .build();

      Request request = new Request.Builder()
          .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
          .post(body)
          .addHeader("content-type", "application/x-www-form-urlencoded")
          .addHeader("Accept-Encoding", "application/gzip")
          .addHeader("X-RapidAPI-Key", "8a3a2122bamshd3241f9f0833b52p17f94ejsn79a5d52dbada")
          .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
          .build();

      Response response = client.newCall(request).execute();
      Gson gson = new Gson();
      ResponseBody responseBody = response.body();
      Map result = gson.fromJson(responseBody.string(), Map.class);

    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }

  static void detect(String input) {
    try {
      OkHttpClient client = new OkHttpClient();

      RequestBody body = new FormBody.Builder()
          .add("q", input)
          .build();

      Request request = new Request.Builder()
          .url("https://google-translate1.p.rapidapi.com/language/translate/v2/detect")
          .post(body)
          .addHeader("content-type", "application/x-www-form-urlencoded")
          .addHeader("Accept-Encoding", "application/gzip")
          .addHeader("X-RapidAPI-Key", "8a3a2122bamshd3241f9f0833b52p17f94ejsn79a5d52dbada")
          .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
          .build();

      Response response = client.newCall(request).execute();
      System.out.println("Response is " + response.message());

      Gson gson = new Gson();
      ResponseBody responseBody = response.body();
      Map result = gson.fromJson(responseBody.string(), Map.class);
      String language = "";
      if(result !=null && result.get("data") != null){
        Map data = (Map) result.get("data");
        List detections = (List) data.get("detections");

        language = (String) ((Map)((List)detections.get(0)).get(0)).get("language");
      }
      System.out.println("Detected language is " + language);
    } catch (Exception ie) {
      ie.printStackTrace(System.out);
    }    
  }


  public static void main(String[] args) {
    detect("మహతి");
  }
}
package the.primer.gems

import java.util.Map
import java.util.List

import groovy.json.JsonSlurper

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.FormBody
import okhttp3.Response

/**
* https://rapidapi.com/referential/api/referential/
*/
class GeoApi {
    static List fetchCitiesForState(String countryCode, String stateCode) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url("https://referential.p.rapidapi.com/v1/city?fields=iso_a2%2Cstate_code%2Cstate_hasc%2Ctimezone%2Ctimezone_offset&iso_a2=${countryCode}&state_hasc=${countryCode}.${stateCode}&limit=5")
            .get()
            .addHeader("X-RapidAPI-Key", "8a3a2122bamshd3241f9f0833b52p17f94ejsn79a5d52dbada")
            .addHeader("X-RapidAPI-Host", "referential.p.rapidapi.com")
            .build();

        Response response = client.newCall(request).execute();

        ResponseBody responseBody = response.body();
        def jsonSlurper = new JsonSlurper()
        def cities = jsonSlurper.parseText(responseBody.string())
        println cities[0]
        println cities[1]
    }

    public static void main(String[] args){
        fetchCitiesForState("IN", "KA")
    }
}
package uz.mk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.mk.model.DicResult;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Main {
    static final String KEY = "dict.1.1.20211002T091939Z.2e4a88e184b9a2a3.e25fb1fc148fb50552c219d440e75afa7e913758";


    public static void main(String[] args) {
        lookUp("en-ru","world");
    }

    public static void lookUp(String lang, String text) {
        try {
            URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=" + KEY + "&lang=" + lang + "&text=" + text);
            URLConnection connection = url.openConnection();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            DicResult dicResult = gson.fromJson(reader, DicResult.class);
            System.out.println(dicResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getLangs(String key) {
        try {
            URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + key);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            URLConnection connection = url.openConnection();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            String[] langs = gson.fromJson(reader, String[].class);
            System.out.println(Arrays.toString(langs));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

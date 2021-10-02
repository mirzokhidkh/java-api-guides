package uz.mk;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;
import uz.mk.model.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Currency> currencies = new ArrayList<>();
        try {
            URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Type type = new TypeToken<ArrayList<Currency>>() {
            }.getType();
            currencies = gson.fromJson(reader, type);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        currencies.forEach(System.out::println);

        int i = 1;
        while (i != 0) {

            String currencyName = "";

            System.out.println("1=>USD;  2=>EUR; 3=>RUB;");
            System.out.print("Select the currency type to convert: ");
            int stepCode = in.nextInt();

            switch (stepCode) {
                case 1:
                    currencyName = "USD";
                    break;
                case 2:
                    currencyName = "EUR";
                    break;
                case 3:
                    currencyName = "RUB";
                    break;
                default:
                    System.out.println("Please type one of the given numbers");
            }
            System.out.print("Enter money amount: ");
            double amount = in.nextDouble();

            for (Currency currency : currencies) {
                if (currency.getCcy().equals(currencyName)) {
                    double value = amount * Double.parseDouble(currency.getRate());
                    System.out.println(value);
                }
            }
        }



    }
}

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //727e688adbac1025cf868c85c5f14e81
    //api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
    public static String getWeather(String messega, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + messega + "&units=metric&appid=727e688adbac1025cf868c85c5f14e81");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        //model.setIcon(main.getString("icon"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));

        }
        return "City : " + model.getName() + "\n" +
                "Tempratura : " + model.getTemp() + "C" + "\n" +
                "Main : " + model.getMain() + "\n" +
                "Humidity : " + model.getHumidity() + "%" + "\n" +
                "http://openweathermap.org/img/w/" + model.getIcon() + ".png";

    }

}

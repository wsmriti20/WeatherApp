package com.testname.weatherapphelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Spinner spinnerMenu;
    private String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the spinner view
        this.spinnerMenu = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerMenu.setAdapter(adapter);
    }


    public void onClick(View view) {

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this);
        final String city_name = this.spinnerMenu.getSelectedItem().toString();
        VolleyLog.e(city_name);
        final Context context = this;
        if(city_name!="Select a city"){

            if (city_name == "Mumbai") {
                urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=19.01&lon=72.84&units=imperial&appid=210f055c323ce838d5b7c8dc64cdc8e4";
            }
            else if (city_name == "Seattle") {
                urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=47.60&lon=-122.33&units=imperial&appid=210f055c323ce838d5b7c8dc64cdc8e4";
            }
            else if (city_name == "Paris") {
                urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=48.85&lon=2.34&units=imperial&appid=210f055c323ce838d5b7c8dc64cdc8e4";
            }
            else {
                urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=42.23&lon=-83.33&units=imperial&appid=210f055c323ce838d5b7c8dc64cdc8e4";
            }


            //create object request
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.GET,    //the request method
                            urlString,  //the URL
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //this prints the WHOLE string
                                    //Log.i("JSON response", response.toString());

                                    try {
                                        Intent intent = new Intent(context, WeatherDisplay.class);
                                        //get description of current weather
                                        JSONObject currentWeather = response.getJSONObject("current");

                                        intent.putExtra("city",city_name);
                                        //getting the current time
                                        Long date = currentWeather.getLong("dt");
                                        java.util.Date time = new java.util.Date((long)date*1000);
                                        intent.putExtra("time", time.toString());

                                        //getting the temp
                                        Double temp = currentWeather.getDouble("temp");
                                        intent.putExtra("temp", temp);

                                        //getting the feels like
                                        Double feels_like = currentWeather.getDouble("feels_like");
                                        intent.putExtra("feels_like", feels_like);

                                        //getting the pressure
                                        Long pressure = currentWeather.getLong("pressure");
                                        intent.putExtra("pressure", pressure);

                                        //getting the humidity
                                        Long humidity = currentWeather.getLong("humidity");
                                        intent.putExtra("humidity", humidity);

                                        //getting the dew point
                                        Double dew_point = currentWeather.getDouble("dew_point");
                                        intent.putExtra("dew_point", dew_point);

                                        //getting the wind speed
                                        Double wind_speed = currentWeather.getDouble("wind_speed");
                                        intent.putExtra("wind_speed", wind_speed);

                                        //getting the weather main
                                        JSONArray weather = currentWeather.getJSONArray("weather");
                                        JSONObject weather_object = weather.getJSONObject(0);
                                        String main = weather_object.getString("main");
                                        intent.putExtra("main", main);
                                        Log.i("main", main);

                                        //getting the description
                                        String weather_desc = weather_object.getString("description");
                                        intent.putExtra("desc",weather_desc);

                                        startActivity(intent);
                                    }
                                    catch(JSONException ex) {
                                        Log.e("JSON Error", ex.getMessage());
                                    }
                                }
                            },
                            new Response.ErrorListener(){

                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    );//end of JSON object request
            requestQueue.add(jsonObjectRequest);
        }


    }

}

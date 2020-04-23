package com.testname.weatherapphelp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class WeatherDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_display);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        TextView time = (TextView) findViewById(R.id.timeView);
        TextView temp = (TextView) findViewById(R.id.editTemp);
        TextView feelsLike = (TextView) findViewById(R.id.editFeelsLike);
        TextView pressure = (TextView) findViewById(R.id.editPressure);
        TextView humidity = (TextView) findViewById(R.id.editHumidity);
        TextView dewPoint = (TextView) findViewById(R.id.editDewPoint);
        TextView windSpeed = (TextView) findViewById(R.id.editWindSpeed);
        TextView weather = (TextView) findViewById(R.id.editWeatherMain);
        TextView description = (TextView) findViewById(R.id.editDescription);

        Intent intent = getIntent();
        Bundle vals = intent.getExtras();

        String cityInfo = vals.getString("city");
        String timeInfo = vals.getString("time");
        Double tempInfo = vals.getDouble("temp");
        Double feelsLikeInfo = vals.getDouble("feels_like");
        Long pressureInfo = vals.getLong("pressure");
        Long humInfo = vals.getLong("humidity");
        Double dewInfo = vals.getDouble("dew_point");
        Double windSpeedInfo = vals.getDouble("dew_point");
        String mainInfo = vals.getString("main");
        String descInfo = vals.getString("desc");

        this.setTitle(cityInfo);
        time.setText(timeInfo);
        temp.setText(tempInfo.toString());
        feelsLike.setText(feelsLikeInfo.toString());
        pressure.setText(pressureInfo.toString());
        humidity.setText(humInfo.toString());
        dewPoint.setText(dewInfo.toString());
        windSpeed.setText(windSpeedInfo.toString());
        weather.setText(mainInfo);
        description.setText(descInfo);
    }

}

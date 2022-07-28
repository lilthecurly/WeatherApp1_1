package com.lilthecurly.weatherapp1_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String BaseUrl = "https://api.weatherapi.com/";
    public static String key = "403a13975be34daeb58175522221307";
    public String q;
    public static String aqi = "no";
    double Latitude;
    double Longitude;
    private ImageView weather;
    private TextView temp, city, datetime, condition, feelslike, pressure, clouds, precip, humidity, visibility, windspeed, windgust, uv, celsius;
    private VideoView videoView;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        permissions();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            videoView = findViewById(R.id.videoView);
            weather = findViewById(R.id.weather);
            temp = findViewById(R.id.temp);
            city = findViewById(R.id.city);
            datetime = findViewById(R.id.datetime);
            condition = findViewById(R.id.condition);
            feelslike = findViewById(R.id.feelslike);
            pressure = findViewById(R.id.pressure);
            clouds = findViewById(R.id.clouds);
            precip = findViewById(R.id.precip);
            humidity = findViewById(R.id.humidity);
            visibility = findViewById(R.id.visibility);
            windspeed = findViewById(R.id.windspeed);
            windgust = findViewById(R.id.windgust);
            uv = findViewById(R.id.uv);
            celsius = findViewById(R.id.celsius);
            Longitude = location.getLongitude();
            Latitude = location.getLatitude();
            q = Latitude + "," + Longitude;
            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.back_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            videoView.start();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            WeatherService service = retrofit.create(WeatherService.class);
            Call<WeatherClass> call = service.getCurrentWeatherData(key,q,aqi);
            call.enqueue(new Callback<WeatherClass>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<WeatherClass> call, Response<WeatherClass> response) {
                    if (response.code() == 200) {
                        WeatherClass weatherClass = response.body();

                        assert weatherClass != null;
                        String uri = weatherClass.getCurrent().getCondition().getIcon();
                        String imageUri = "https:" + uri;
                        String imageDone = imageUri.replace("64x64", "128x128");
                        Picasso.with(getApplicationContext()).load(imageDone).into(weather);
                        double tmp = weatherClass.getCurrent().getTempC();
                        int Temperature = (int) tmp;
                        temp.setText("" + Temperature);
                        city.setText(weatherClass.getLocation().getName());
                        datetime.setText(weatherClass.getLocation().getLocaltime());
                        condition.setText(weatherClass.getCurrent().getCondition().getText());
                        feelslike.setText("\n  Feels like " + weatherClass.getCurrent().getFeelslikeC().toString() + " °C");
                        pressure.setText("\n  Pressure " + weatherClass.getCurrent().getPressureMb().toString() + " Mb");
                        clouds.setText("\n  Cloudiness " + weatherClass.getCurrent().getCloud().toString() + " %");
                        precip.setText("\n  Precipitation " + weatherClass.getCurrent().getPrecipMm().toString() + " mm");
                        humidity.setText("\n  Humidity " + weatherClass.getCurrent().getHumidity().toString() + " %");
                        visibility.setText("\n  Visibility " + weatherClass.getCurrent().getVisKm().toString() + " Km");
                        windspeed.setText("\n  Wind speed " + weatherClass.getCurrent().getWindKph().toString() + " Km/h");
                        windgust.setText("\n  Gust speed " + weatherClass.getCurrent().getGustKph().toString() + " Km/h");
                        uv.setText("UV index " + weatherClass.getCurrent().getUv().toString());
                        celsius.setText("°C");
                    }
                }
                @Override
                public void onFailure(Call<WeatherClass> call, Throwable t) {
                    condition.setText(t.getMessage());
                }
            });
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

    }

    @Override
    protected void onResume(){
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){
        videoView.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        videoView.stopPlayback();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {}

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void permissions() {
        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
    }

}

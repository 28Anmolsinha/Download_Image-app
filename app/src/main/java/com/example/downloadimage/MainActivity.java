package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
 ImageView downloadedImg;
    public void downloadImage(View view) throws ExecutionException, InterruptedException {
    //https://i.pinimg.com/originals/64/aa/17/64aa17d64b3784e5317f040b673220c0.png
    ImageDownloader task= new ImageDownloader();
    Bitmap myImage;

    myImage=task.execute("https://i.pinimg.com/originals/64/aa/17/64aa17d64b3784e5317f040b673220c0.png").get();
downloadedImg.setImageBitmap(myImage);
    //Log.i("Interaction","Button tapped");
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         downloadedImg=(ImageView)  findViewById(R.id.imageview);

}
public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream=connection.getInputStream();
            Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
            return myBitmap;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

}
package com.websarva.wings.android.sampleinternetimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.net.Uri;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskHttpRequest extends AsyncTask<Uri.Builder, Void, Bitmap>{
    private ImageView imageView;

    public AsyncTaskHttpRequest(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Uri.Builder... builder){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;

        try{

            URL url = new URL(builder[0].toString());
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            inputStream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (MalformedURLException exception){

        }catch (IOException exception){

        }finally {
            if (connection != null){
                connection.disconnect();
            }
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException exception){
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result){
        this.imageView.setImageBitmap(result);
    }
}
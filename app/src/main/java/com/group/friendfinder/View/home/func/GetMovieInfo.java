package com.group.friendfinder.View.home.func;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMovieInfo extends AppCompatActivity {
    private String movieTitle = "";
    private String webRes = "";
    private String moviePlot = "";
    private String moviePosterURL = "";
    private String movieGenre = "";
    private String movieReleased = "";
    private String movieRuntime = "";
    private String movieCountry = "";
    private String movieLanguage = "";
    private String movieRating = "";
    private String imdbVotes = "";
    private RatingBar ratingBar;
    private TextView ratingTextView;
    private TextView votesTextView;
    private TextView movieTitleTextView;
    private TextView moviePlotTextView;
    private TextView movieBriefTextView;
    private ImageView movieImg;

    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.movie_detail);
//        movieTitle = "The Avengers";
        movieTitle = "Game of Thrones";
        movieTitle = intent.getStringExtra("movie");

        ratingBar = findViewById(R.id.ratingBar);
        ratingTextView = findViewById(R.id.ratingTextView);
        votesTextView = findViewById(R.id.votesTextView);
        moviePlotTextView = findViewById(R.id.moviePlotTextView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieBriefTextView = findViewById(R.id.movieBriefTextView);
        movieImg = findViewById(R.id.movieImg);

        new getWebAsyncTask().execute("http://www.omdbapi.com/?apikey=7c1f6129&t=" + movieTitle);

//
//        SharedPreferences spUserInfo = getSharedPreferences("spUserInfo",
//                Context.MODE_PRIVATE);
//        String UserInfo = spUserInfo.getString("UserInfo", "");
//        System.out.println(UserInfo);

    }

    /**
     * 获取指定URL的响应字符串
     * @param urlString
     * @return
     */
    private String getURLResponse(String urlString){
        HttpURLConnection conn = null; //连接对象
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString); //URL对象
            conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setRequestMethod("GET"); //使用get请求
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine  = "";
            while((inputLine = bufferReader.readLine()) != null){
                resultData += inputLine + "\n";
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }

        return resultData;
    }

    /**
     * 从指定URL获取图片
     * @param url
     * @return
     */
    private Bitmap getImageBitmap(String url){
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    class getWebAsyncTask extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... params) {
            return getURLResponse(params[0]);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String ret) {
            webRes = ret;

            String patternTitle = "\"Title\":\"(.*?)\"";
            String patternPlot = "\"Plot\":\"(.*?)\"";
            String patternPoster = "\"Poster\":\"(.*?)\"";
            String patternGenre = "\"Genre\":\"(.*?)\"";
            String patternReleased = "\"Released\":\"(.*?)\"";
            String patternRuntime = "\"Runtime\":\"(.*?)\"";
            String patternCountry = "\"Country\":\"(.*?)\"";
            String patternLanguage = "\"Language\":\"(.*?)\"";
            String patternRating = "\"imdbRating\":\"(.*?)\"";
            String patternVotes = "\"imdbVotes\":\"(.*?)\"";


            // Create a Pattern object
            Pattern rTitle = Pattern.compile(patternTitle);
            Pattern rPlot = Pattern.compile(patternPlot);
            Pattern rPoster = Pattern.compile(patternPoster);
            Pattern rGenre = Pattern.compile(patternGenre);
            Pattern rReleased = Pattern.compile(patternReleased);
            Pattern rRuntime = Pattern.compile(patternRuntime);
            Pattern rCountry = Pattern.compile(patternCountry);
            Pattern rLanguage = Pattern.compile(patternLanguage);
            Pattern rRating = Pattern.compile(patternRating);
            Pattern rVotes = Pattern.compile(patternVotes);

            // Now create matcher object.
            Matcher mTitle = rTitle.matcher(webRes);
            Matcher mPlot = rPlot.matcher(webRes);
            Matcher mPoster = rPoster.matcher(webRes);
            Matcher mGenre = rGenre.matcher(webRes);
            Matcher mReleased = rReleased.matcher(webRes);
            Matcher mRuntime = rRuntime.matcher(webRes);
            Matcher mCountry = rCountry.matcher(webRes);
            Matcher mLanguage = rLanguage.matcher(webRes);
            Matcher mRating = rRating.matcher(webRes);
            Matcher mVotes = rVotes.matcher(webRes);
            if(mVotes.find() && mTitle.find() && mPlot.find() && mPoster.find() && mGenre.find() && mReleased.find() && mRuntime.find() && mCountry.find() && mLanguage.find() && mRating.find()){
                movieTitle = mTitle.group(1);
                moviePlot = mPlot.group(1);
                moviePosterURL = mPoster.group(1);
                movieGenre = mGenre.group(1);
                movieReleased = mReleased.group(1);
                movieRuntime = mRuntime.group(1);
                movieCountry = mCountry.group(1);
                movieLanguage = mLanguage.group(1);
                movieRating = mRating.group(1);
                imdbVotes = mVotes.group(1);
            }

            new DownImgAsyncTask().execute(moviePosterURL);
            movieTitleTextView.setText(movieTitle);
            moviePlotTextView.setText(moviePlot);
            movieBriefTextView.setText("Genre: " + movieGenre + "\nReleased: " + movieReleased + "\nRuntime: " + movieRuntime + "\nCountry: " + movieCountry + "\nLanguage: " + movieLanguage);
            ratingTextView.setText(movieRating);
            votesTextView.setText(imdbVotes + " votes");
            ratingBar.setRating(Float.parseFloat(movieRating) / 2);

            System.out.println(ret);
        }
    }

    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Bitmap b = getImageBitmap(params[0]);
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(result!=null){
                movieImg.setImageBitmap(result);
            }
        }

    }
}

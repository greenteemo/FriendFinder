package com.group.friendfinder.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.group.friendfinder.Friendship;
import com.group.friendfinder.Location;
import com.group.friendfinder.Profile;

import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// import com.google.gson.Gson;

public class RestClient {
    private static final String BASE_URI = "http://z2462w1583.qicp.vip:29989/MonashFriendFinder/webresources";

    /*
    Task3 Login
    (a) get student
     */
    public static String login(String smonashEmail, String spassword) {
        final String methodPath = "/monashfriendfinder.mffprofile/login/" + smonashEmail + "/" + spassword;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    //monashfriendfinder.mfffriendship/findByStudentid/{studentid}
    /*
    Task4
    (e)
     */
    public static String getFriend(Integer studentid) {
        final String methodPath = "/monashfriendfinder.mfffriendship/findByStudentid/" + studentid;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static void deleteFriend(String friendshipId){
        final String methodPath ="/monashfriendfinder.mfffriendship/"+ friendshipId;
        URL url = null;
        HttpURLConnection conn = null;
        // Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the connection method to GET
            conn.setRequestMethod("DELETE");
            Log.i("code",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    /*
    Task4 Editing Records and Search Screen
    (b) get students by keys
     */
    public static String getStudentsByKeys(Integer studentid, Integer smonashEmail, Integer spassword, Integer firstname, Integer surname, Integer dateOfBirth, Integer gender, Integer nationality, Integer nativeLanguage, Integer address, Integer suburb, Integer course, Integer studyMode, Integer job, Integer favorSport, Integer favorMovie, Integer favorUnit, Integer subscribeData, Integer subscribeTime) {
        final String methodPath = "/monashfriendfinder.mffprofile/findByKeys/"+ studentid + "/" + smonashEmail + "/" + spassword + "/" + firstname + "/" + surname + "/" + dateOfBirth + "/" + gender + "/" + nationality + "/" + nativeLanguage + "/" + address + "/" + suburb + "/" + course + "/" + studyMode + "/" + job + "/" + favorSport + "/" + favorMovie + "/" + favorUnit + "/" + subscribeData + "/" + subscribeTime;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    /*
    Task7 Reports
    (a) get favorite units
     */
    public static String getFavorUnits() {
        final String methodPath = "/monashfriendfinder.mffprofile/favorUnits";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    /*
    Task7 Reports
    (b) get locations frequency visited
     */
    public static String getVistedTimes(Integer studentId, String startDate, String endDate) {
        final String methodPath = "/monashfriendfinder.mfflocation/locationsVisited/" + studentId + "/" + startDate + "/" + endDate;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    /*
    post Profile
     */
    public static void postProfile(Profile profile){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/monashfriendfinder.mffprofile/";
        try {
            Gson gson =new Gson();
            String stringProfileJson=gson.toJson(profile);
            System.out.print(stringProfileJson);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringProfileJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringProfileJson);
            out.close();
            Log.i("code",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    /*
    post Location
     */
    public static void postLocation(Location location){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/monashfriendfinder.mfflocation/";
        try {
            Gson gson =new Gson();
            String stringProfileJson=gson.toJson(location);
            System.out.print(stringProfileJson);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringProfileJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringProfileJson);
            out.close();
            Log.i("code",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    /*
    post Friendship
     */
    public static String postFriendship(Friendship friendship){
        //initialise
        String ret = "";
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/monashfriendfinder.mfffriendship/";
        try {
            Gson gson =new Gson();
            String stringProfileJson=gson.toJson(friendship);
            System.out.print(stringProfileJson);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringProfileJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringProfileJson);
            out.close();
            Log.i("code",new Integer(conn.getResponseCode()).toString());
            ret = new Integer(conn.getResponseCode()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return ret;
    }

    // get student by id
    public static String getStudent(Integer id) {
        final String methodPath = "/monashfriendfinder.mffprofile/findByStudentid/" + id;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // get most recent location by studentid
    public static String getMostRecentLoc(Integer studentid) {
        final String methodPath = "/monashfriendfinder.mfflocation/RecentLocation/" + studentid;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while(inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // updateProfile
    public static void updateProfile(Integer studentid, Profile profile){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/monashfriendfinder.mffprofile/" + studentid;
        try {
            Gson gson =new Gson();
            String stringProfileJson=gson.toJson(profile);
            System.out.print(stringProfileJson);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to PUT
            conn.setRequestMethod("PUT");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringProfileJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringProfileJson);
            out.close();
            Log.i("code",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    // create profile by studentid
    public static Profile createProfile(String studentid){
        Profile profile = new Profile();

        String patternsmonashEmail = "\"smonashEmail\":\"(.*?)\"";
        String patternspassword = "\"spassword\":\"(.*?)\"";
        String patternfirstname = "\"firstname\":\"(.*?)\"";
        String patternsurname = "\"surname\":\"(.*?)\"";
        String patterndateOfBirth = "\"dateOfBirth\":\"(.*?)\"";
        String patterngender = "\"gender\":\"(.*?)\"";
        String patternnationality = "\"nationality\":\"(.*?)\"";
        String patternnativeLanguage = "\"nativeLanguage\":\"(.*?)\"";
        String patternaddress = "\"address\":\"(.*?)\"";
        String patternsuburb = "\"suburb\":\"(.*?)\"";
        String patterncourse = "\"course\":\"(.*?)\"";
        String patternstudyMode = "\"studyMode\":\"(.*?)\"";
        String patternjob = "\"job\":\"(.*?)\"";
        String patternfavorSport = "\"favorSport\":\"(.*?)\"";
        String patternfavorMovie = "\"favorMovie\":\"(.*?)\"";
        String patternfavorUnit = "\"favorUnit\":\"(.*?)\"";
        String patternsubscribeData = "\"subscribeData\":\"(.*?)\"";
        String patternsubscribeTime = "\"subscribeTime\":\"(.*?)\"";

        // Create a Pattern object
        Pattern rsmonashEmail = Pattern.compile(patternsmonashEmail);
        Pattern rspassword = Pattern.compile(patternspassword);
        Pattern rfirstname = Pattern.compile(patternfirstname);
        Pattern rsurname = Pattern.compile(patternsurname);
        Pattern rdateOfBirth = Pattern.compile(patterndateOfBirth);
        Pattern rgender = Pattern.compile(patterngender);
        Pattern rnationality = Pattern.compile(patternnationality);
        Pattern rnativeLanguage = Pattern.compile(patternnativeLanguage);
        Pattern raddress = Pattern.compile(patternaddress);
        Pattern rsuburb = Pattern.compile(patternsuburb);
        Pattern rcourse = Pattern.compile(patterncourse);
        Pattern rstudyMode = Pattern.compile(patternstudyMode);
        Pattern rjob = Pattern.compile(patternjob);
        Pattern rfavorSport = Pattern.compile(patternfavorSport);
        Pattern rfavorMovie = Pattern.compile(patternfavorMovie);
        Pattern rfavorUnit = Pattern.compile(patternfavorUnit);
        Pattern rsubscribeData = Pattern.compile(patternsubscribeData);
        Pattern rsubscribeTime = Pattern.compile(patternsubscribeTime);

        String studentInfo = getStudent(Integer.parseInt(studentid));
        System.out.println(studentInfo);

        // Now create matcher object.
        Matcher msmonashEmail = rsmonashEmail.matcher(studentInfo);
        Matcher mspassword = rspassword.matcher(studentInfo);
        Matcher mfirstname = rfirstname.matcher(studentInfo);
        Matcher msurname = rsurname.matcher(studentInfo);
        Matcher mdateOfBirth = rdateOfBirth.matcher(studentInfo);
        Matcher mgender = rgender.matcher(studentInfo);
        Matcher mnationality = rnationality.matcher(studentInfo);
        Matcher mnativeLanguage = rnativeLanguage.matcher(studentInfo);
        Matcher maddress = raddress.matcher(studentInfo);
        Matcher msuburb = rsuburb.matcher(studentInfo);
        Matcher mcourse = rcourse.matcher(studentInfo);
        Matcher mstudyMode = rstudyMode.matcher(studentInfo);
        Matcher mjob = rjob.matcher(studentInfo);
        Matcher mfavorSport = rfavorSport.matcher(studentInfo);
        Matcher mfavorMovie = rfavorMovie.matcher(studentInfo);
        Matcher mfavorUnit = rfavorUnit.matcher(studentInfo);
        Matcher msubscribeData = rsubscribeData.matcher(studentInfo);
        Matcher msubscribeTime = rsubscribeTime.matcher(studentInfo);
        if(msmonashEmail.find() && mspassword.find() && mfirstname.find() && msurname.find() && mdateOfBirth.find() && mgender.find() && mnationality.find() && mnativeLanguage.find() && maddress.find() && msuburb.find() && mcourse.find() && mstudyMode.find() && mjob.find() && mfavorSport.find() && mfavorMovie.find() && mfavorUnit.find() && msubscribeData.find() && msubscribeTime.find()){
            System.out.println(studentInfo);
            profile = new Profile(Integer.parseInt(studentid), msmonashEmail.group(1), mspassword.group(1), mfirstname.group(1), msurname.group(1), mdateOfBirth.group(1), mgender.group(1).charAt(0), mnationality.group(1), mnativeLanguage.group(1), maddress.group(1), msuburb.group(1), mcourse.group(1), mstudyMode.group(1).charAt(0), mjob.group(1), mfavorSport.group(1), mfavorMovie.group(1), mfavorUnit.group(1), msubscribeData.group(1), msubscribeTime.group(1));
            return profile;
        }
        return profile;
    }
}
package com.debuggerme.util;

import com.debuggerme.controller.HomeController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.TimerTask;

public class StreamRecorder extends TimerTask {

    private final String USER_AGENT = "Mozilla/5.0";


    private synchronized void sendGet() throws Exception {

        String url = "http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/chunklist_w290202276.m3u8";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String[] split = response.toString().split(",");
        String param = split[3].split("#")[0];

//        System.out.println(response.toString());

        try{
            URLConnection conn = new URL("http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/"+param).openConnection();
            InputStream is = conn.getInputStream();

            OutputStream outstream = new FileOutputStream(new File(HomeController.dirPath+"/Rec"+ LocalDate.now().toString()),true);
            byte[] buffer = new byte[4096];
            int len;
            long t = System.currentTimeMillis();
            while ((len = is.read(buffer)) > 0 && System.currentTimeMillis() - t <= 5000) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
        }
        catch(Exception e){
            System.out.print(e);
        }

    }

    @Override
    public void run() {
        try {
            sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

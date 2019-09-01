package com.debuggerme.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

public class StreamDownloader extends TimerTask {

    private String chunkUrl;
    private String stationUrl;
    private String dirPath;
    private String fileName;


    public StreamDownloader(String chunkUrl, String stationUrl, String dirPath, String fileName) {
        this.chunkUrl = chunkUrl;
        this.stationUrl = stationUrl;
        this.dirPath = dirPath;
        this.fileName = fileName;

    }

    private synchronized void sendGet(String chunkUrl, String stationUrl, String dirPath, String fileName) throws Exception {


        URL obj = new URL(chunkUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + chunkUrl);
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

        System.out.println(response.toString());

        try{
            URLConnection conn = new URL(stationUrl+param).openConnection();
            InputStream is = conn.getInputStream();
            System.out.println(dirPath+"/"+fileName+".mp3");
            OutputStream outstream = new FileOutputStream(new File(dirPath+"/"+fileName+".mp3"),true);
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
            sendGet(chunkUrl,stationUrl,dirPath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

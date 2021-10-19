package me.pk2.bugtester;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class PostRequest {
    public static HttpURLConnection genCon(String Url) throws Exception {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        return con;
    }
    public static HttpURLConnection genGetCon(String Url) throws Exception {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        con.setDoOutput(true);

        return con;
    }
    public static HttpURLConnection genUnOutCon(String Url) throws Exception {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        return con;
    }

    public static HttpURLConnection sendString(HttpURLConnection con, String data) throws IOException {
        try(OutputStream os = con.getOutputStream()) {
            byte[] in = data.getBytes("utf-8");
            os.write(in, 0, in.length);
        }

        return con;
    }
    public static String read(HttpURLConnection con) throws Exception {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null)
                response.append(responseLine.trim());

            return response.toString();
        }
    }

    public static void downloadFile(String url, String path) {
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            connection.addRequestProperty("User-Agent", "aaa");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(120000);
            // FileUtils.copyInputStreamToFile(connection.getInputStream(), new File(path));
        } catch (Exception exception) { exception.printStackTrace(); }
    }
}
package acs;

import acs.entities.Station;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * Class that is used to download data as JSON format
 */
public class Utility {
    /**
     * @param rd reader that is ready to return whole JSON text format
     * @return string representing JSON object
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * @param url url that will be used to get data as JSON text format
     * @return string representing JSON object
     * @throws IOException
     */
    private static String readJsonFromUrl(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            return readAll(rd);
        } finally {
            inputStream.close();
        }
    }

    /**
     * @return JSON response representing all stations
     * @throws IOException
     */
    public static String getAll() throws IOException{
        return readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/station/findAll");
    }

    /**
     * @param id id of station
     * @return JSON representing air index of selected station id
     * @throws IOException
     * @see Station
     */
    public static String getIndex(int id) throws IOException{
        String path = new java.io.File(".").getCanonicalPath()+"\\cache\\index_" + id + ".txt";
        File file = new File(path);
        if(file.exists()){
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date fileDate;
            Date currentDate = new Date();
            try{
                fileDate = sdf.parse(sdf.format(file.lastModified()));
            }
            catch(Exception e){
                throw new IllegalArgumentException("Błąd formatowania daty");
            }
            if(Math.abs(fileDate.getTime() - currentDate.getTime()) < 3600000 && fileDate.getHours() == currentDate.getHours()){
                return new Scanner(file).useDelimiter("\\Z").next();
            }
            else{
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/" + Integer.toString(id));
                writer.write(result);
                writer.close();
                return result;
            }
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/" + Integer.toString(id));
        writer.write(result);
        writer.close();
        return result;
    }

    /**
     * @param id id of station
     * @return JSON text format representing stands of selected station id
     * @throws IOException
     */
    public static String getStands(int id) throws IOException{
        String path = new java.io.File(".").getCanonicalPath()+"\\cache\\stand_" + id + ".txt";
        File file = new File(path);
        if(file.exists()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date fileDate;
            Date currentDate = new Date();
            try{
                fileDate = simpleDateFormat.parse(simpleDateFormat.format(file.lastModified()));
            }
            catch(Exception e){
                throw new IllegalArgumentException("Błąd formatowania daty");
            }
            if(Math.abs(fileDate.getTime() - currentDate.getTime()) < 3600000 && fileDate.getHours() == currentDate.getHours()){
                return new Scanner(file).useDelimiter("\\Z").next();
            }
            else{
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + Integer.toString(id));
                writer.write(result);
                writer.close();
                return result;
            }
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + Integer.toString(id));
        writer.write(result);
        writer.close();
        return result;
    }

    /**
     * @param id id of selected stand
     * @return JSON text format representing records of selected stand id
     * @throws IOException
     */
    public static String getRecords(int id) throws IOException{
        String path = new java.io.File(".").getCanonicalPath()+"\\cache\\record_" + id + ".txt";
        File file = new File(path);
        if(file.exists()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date fileDate;
            Date currentDate = new Date();
            try{
                fileDate = simpleDateFormat.parse(simpleDateFormat.format(file.lastModified()));
            }
            catch(Exception e){
                throw new IllegalArgumentException("Incorrect data format");
            }
            if(Math.abs(fileDate.getTime() - currentDate.getTime()) < 3600000 && fileDate.getHours() == currentDate.getHours()){
                return new Scanner(file).useDelimiter("\\Z").next();
            }
            else{
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + Integer.toString(id));
                writer.write(result);
                writer.close();
                return result;
            }
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        String result = readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + Integer.toString(id));
        writer.write(result);
        writer.close();
        return result;
    }
}

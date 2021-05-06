package com.example.resetmara;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellService {
    private static final String TAG = "ADBService";
    ShellService(){

    }

    public String comandExe(String[] command) {

        StringBuilder stringBuilder = new StringBuilder();
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;

        try{
            Process process = processBuilder.start();
            inputStream = process.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(inputStream != null){
                    inputStream.close();
                }
                if(inputStreamReader != null){
                    inputStreamReader.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}

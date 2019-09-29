package com.example.resetmara;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class AsyncInputConnect extends AsyncTask<String, Void, String> {
    Socket connection = null;
    BufferedWriter writer = null;

    @Override
    protected String doInBackground(String... string) {
        try {
            // サーバーへ接続
            connection = new Socket("127.0.0.1", 8081);

            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

            for (String str : string) {
                writer.write(str);
                writer.newLine();
            }

            writer.flush();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.println(Log.DEBUG,"UnknownHostException","サーバーとの接続に失敗しました。");
        } catch (IOException e) {
            e.printStackTrace();
            Log.println(Log.DEBUG,"IOException","サーバーとの接続に失敗しました。");
        } finally {
            try {
                // 接続終了処理
                if (writer != null) {
                    writer.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.println(Log.DEBUG,"","サーバーとの接続に失敗しました。");
            }
        }
        return "";
    }
}

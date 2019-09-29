package com.example.resetmara;

import android.view.KeyEvent;

import androidx.core.view.InputDeviceCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class InputHost {
    public static void main(String args[]) {

        InputService inputService = null;
        try {
            inputService = new InputService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ServerSocket listener = null;
        try {
            listener = new ServerSocket();
            listener.setReuseAddress(true);
            listener.bind(new InetSocketAddress(8081));
            System.out.println("Server listening on port 8081...");

            boolean runnning = true;
            while (runnning) {
                Socket clientSocket = listener.accept();//接続まで待機
                System.out.println("Connected");

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while (true) {
                    String msg = reader.readLine();
                    System.out.println(msg);

                    if (msg == null) {
                        break;
                    }

                    String[] data = msg.split(" ");

                    if (data.length > 0) {
                        if (data[0].equals("screen")) {//タッチデータの場合
                            inputService.injectMotionEvent(InputDeviceCompat.SOURCE_TOUCHSCREEN, Integer.valueOf(data[1]), Integer.valueOf(data[2]), Integer.valueOf(data[3]));
                        } else if (data[0].equals("key")) {//キーの場合
                            inputService.injectKeyEvent(new KeyEvent(Integer.valueOf(data[1]), Integer.valueOf(data[2])));
                        } else if (data[0].equals("exit")) {//終了コール
                            runnning = false;
                        }
                    }
                }

                clientSocket.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (listener != null) {
                    listener.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

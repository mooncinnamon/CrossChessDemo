package com.cinnamon;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1 대 1 소켓 통신 서버 예제
 */
public class Main {
    private ServerSocket mServerSocket;
    private Socket mSocket;

    private BufferedReader mIn;    // 들어오는 통로
    private PrintWriter mOut;  // 나가는 통로

    private String[] moveArray;
    private int count = 0;

    public Main() {
        try {
            mServerSocket = new ServerSocket(15555);
            System.out.println("서버 시작!!!");
            // 스레드가 멈춰 있고

            while (true) {
                // 연결 요청이 들어오면 연결
                mSocket = mServerSocket.accept();
                System.out.println("클라이언트와 연결 됨");

                mIn = new BufferedReader(
                        new InputStreamReader(mSocket.getInputStream()));

                mOut = new PrintWriter(mSocket.getOutputStream());

                // 클라이언트에서 보낸 문자열 출력

                String message = mIn.readLine();
                System.out.println(message);

                if (message.equals("#finishGame")) {
                    break;
                }
                // 클라이언트에 문자열 전송

                moveArray = new String[]{"f5", "g5", "a5"};
                mOut.println(moveArray[count]);

                mOut.flush();
                System.out.println("sendMessage : " + moveArray[count]);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 소켓 닫기 (연결 끊기)
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Main server = new Main();
    }
}
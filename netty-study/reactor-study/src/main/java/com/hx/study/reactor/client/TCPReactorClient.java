package com.hx.study.reactor.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TCPReactorClient {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        String hostname = "127.0.0.1";
        int port = 1333;
        System.out.println("Connecting to " + hostname + ":" + port);
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(hostname,port));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String input = stdIn.readLine();
            if (socketChannel.finishConnect()) {
//                while (input != null) {
//                    if (input.equals("exit")) {
//                        break;
//                    }
                    writeBuffer.clear();
                    writeBuffer.put(input.getBytes());
                    writeBuffer.flip();
                    while (writeBuffer.hasRemaining()) {
                        socketChannel.write(writeBuffer);
                    }
                    socketChannel.read(readBuffer);
                    System.out.println("---->"+getString(readBuffer));
//                }
            }




//            Socket client = new Socket(hostname, port); // 連接至目的地
//            System.out.println("Connected to " + hostname);
//            PrintWriter out = new PrintWriter(client.getOutputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            String input;
//            while ((input = stdIn.readLine()) != null) { // 讀取輸入
//                out.println(input); // 發送輸入的字符串
//                out.flush(); // 強制將緩衝區內的數據輸出
//                if (input.equals("exit")) {
//                    break;
//                }
//                System.out.println("server: " + in.readLine());
//            }
//            client.close();
//            System.out.println("client stop.");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the socket connection");
        }
    }

    public static String getString(ByteBuffer buffer) {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
}

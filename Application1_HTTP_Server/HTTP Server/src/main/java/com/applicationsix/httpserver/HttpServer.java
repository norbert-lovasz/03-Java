package com.applicationsix.httpserver;

import com.applicationsix.httpserver.config.Configuration;
import com.applicationsix.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
Driver Class for Http  Server

*/

public class HttpServer {

    public static void main (String[] args) {

       System.out.println("Server starting ...");

       ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
       Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

       System.out.println("Using Port: " + conf.getPort());
       System.out.println("Using Webroot: " + conf.getWebroot());

       System.out.println("Open in browser: http://localhost:8080/ ");

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputstring = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();


            String html = "<html><head><title>Simple Java HTML</title></head><body><h1>This page was served using simple Java HTTP Server</h1></body>   </html>";

            final String CRLF = "\n\r"; //13, 10

            String response =
                     "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTP_VERSION_RESPONSE_CODE_MESSAGE
                      "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                                      CRLF +
                                      html +
                                      CRLF + CRLF ;
            outputStream.write(response.getBytes());

            //To do we would write

            inputstring.close();
            outputStream.close();
            socket.close();
            serverSocket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}

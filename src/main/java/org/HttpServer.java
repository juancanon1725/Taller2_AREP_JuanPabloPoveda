package org;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase principal que contiene el metodo main que inicia el servidor http
 */
public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Esperando comunicación del cliente ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String uriString = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(inputLine.contains("title?name")){
                    String[] firstSplit = inputLine.split("=");
                    uriString = (firstSplit[1].split("HTTP"))[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if(!Objects.equals(uriString, "")){
                outputLine = getHello(uriString);
            }else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static String getHello(String uri) throws IOException {

        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>"+
                createTabla(Cache.searchTitulo(uri));
        return response;
    }

    public static String getIndexResponse(){
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Search Movies </h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Title:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" + "<br>"+
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/title?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "</html>";
        return response;
    }

    private static String createTabla(String respuestaApi){
        String[] datos = respuestaApi.split(":");
        String tabla = "<table> \n";
        for (int i = 0;i<(datos.length);i++) {
            String[] respuestaTemporal = datos[i].split(",");
            tabla+="<td>" + Arrays.toString(Arrays.copyOf(respuestaTemporal, respuestaTemporal.length - 1)).replace("[","").replace("]","").replace("}","") + "</td>\n</tr>\n";
            tabla+="<tr>\n<td>" +  respuestaTemporal[respuestaTemporal.length-1].replace("{","").replace("[","") + "</td>\n";
        }
        tabla += "</table>";
        return tabla;

    }

    public static String getHtttpResponse(URI uri) throws IOException {
        Path file = Paths.get("target/classes/public" + uri.getPath());
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n";

        Charset charset = Charset.forName("UTF-8");
        BufferedReader reader = Files.newBufferedReader(file, charset);
        String line = null;

        while ((line = reader.readLine())!= null){
            System.out.println(line);
            outputLine = outputLine + line;
        }



        return outputLine;

    }


}
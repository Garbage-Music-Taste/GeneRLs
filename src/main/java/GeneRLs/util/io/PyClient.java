package GeneRLs.util.io;

import processing.core.PApplet;
import processing.data.JSONObject;
import processing.net.Client;

public class PyClient {
    Client client;
    String buffer = "";
    PApplet app;

    public PyClient(PApplet applet, String host, int port){
        client = new Client(applet, host, port);
        this.app = applet;
    }

    public void send(JSONObject json){
     //   System.out.println("Sending " + json.toString());
        if(client != null && client.active()){
            client.write(json.toString().replaceAll("\\s+", "") + "\n");
         //   System.out.println("Sent");
        }
    }

    public JSONObject receive(){
        if(client.available() > 0){
          //  System.out.println("Receiving " + client.toString());
            buffer += client.readString();
            int newLine = buffer.indexOf("\n");
            if(newLine != -1){
                String line = buffer.substring(0, newLine);
                buffer = buffer.substring(newLine + 1);
                return app.parseJSONObject(line);
            }
        }
        return null; // if nothing received
    }

    public void close(){
        client.stop();
    }
}

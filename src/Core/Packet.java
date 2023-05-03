package Core;

import com.google.gson.Gson;

import static Core.Menu.*;

public class Packet {
    private ClientMenu requestType;
    private String payload;

    private String ids;
    public Packet(ClientMenu requestType,String payload){
        this.payload = payload;
        this.requestType = requestType;
        this.ids = "";
    }

    public String getIds() {
        return ids;
    }

    public Packet(){
        this.ids = "";
        this.payload = "";
        this.requestType = null;
    }
    public ClientMenu getRequestType() {
        return requestType;
    }
    public String getPayload(){
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setRequestType(ClientMenu requestType) {
        this.requestType = requestType;
    }

    public String writeToJSON()
    {
        Gson gsonParser = new Gson();
        return gsonParser.toJson(this);
    }

    public void readFromJSON(String jsonString)
    {
        Gson gsonParser = new Gson();

        Packet temp = gsonParser.fromJson(jsonString, Packet.class);
        this.requestType = temp.getRequestType();
        this.payload = temp.getPayload();
        this.ids = temp.getIds();
    }

}

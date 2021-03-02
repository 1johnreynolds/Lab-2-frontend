package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.concurrent.CompletionStage;


public class Publication {
    private Long pid;

    private String Title;

    private String Metadata;

    //private Long Id;

    //public String getId() { return Long.toString(Id);}

    public Long getID() {
        return pid;
    }

    public void setID(Long pid) {
        this.pid = pid;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getMetadata() {
        return Metadata;
    }

    public void setMetadata(String metadata) {
        this.Metadata = metadata;
    }


    //public void setId(String id) {
    //    this.Id = Long.parseLong(id);
    //}

    public CompletionStage<WSResponse> checkAuthorized() {

        WSClient ws = play.test.WSTestClient.newClient(9005);
        //add Title
        WSRequest request = ws.url("http://localhost:9005/query1Response");
        ObjectNode res = Json.newObject();
        res.put("Title", this.Title);
        //res.put("Id", this.Id);

        return request.addHeader("Content-Type", "application/json")
                .post(res)
                .thenApply((WSResponse r) -> {
                    return r;
                });
    }


}

package com.ductho.nguphaptienganh.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

@SerializedName("response_code")
@Expose
private Integer responseCode;
@SerializedName("results")
@Expose
private List<Result> results = null;

public Integer getResponseCode() {
return responseCode;
}

public void setResponseCode(Integer responseCode) {
this.responseCode = responseCode;
}

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

    @Override
    public String toString() {
        return "ApiResponse{" +
                "responseCode=" + responseCode +
                ", results=" + results +
                '}';
    }
}
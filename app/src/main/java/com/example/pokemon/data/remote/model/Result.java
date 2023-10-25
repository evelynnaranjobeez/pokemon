package com.example.pokemon.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        String s = url;
        s = s.substring(0, s.length() - (s.endsWith("/") ? 1 : 0));
        return s;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

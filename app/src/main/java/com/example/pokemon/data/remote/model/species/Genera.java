package com.example.pokemon.data.remote.model.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genera {

    @SerializedName("genus")
    @Expose
    private String genus;

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

}

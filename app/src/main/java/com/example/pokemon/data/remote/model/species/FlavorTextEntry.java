package com.example.pokemon.data.remote.model.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlavorTextEntry {

    @SerializedName("flavor_text")
    @Expose
    private String flavorText;

    public String getFlavorText() {
        return flavorText.replace("\n", " ");
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

}
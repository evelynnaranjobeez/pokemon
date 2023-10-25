package com.example.pokemon.data.remote.model.move;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EffectEntry {
    @SerializedName("effect")
    @Expose
    private String effect;

    @SerializedName("short_effect")
    @Expose
    private String shortEffect;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }
}

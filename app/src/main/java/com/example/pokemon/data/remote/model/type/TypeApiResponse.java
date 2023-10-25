package com.example.pokemon.data.remote.model.type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeApiResponse {
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private Type type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}

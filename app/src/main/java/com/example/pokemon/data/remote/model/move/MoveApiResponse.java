package com.example.pokemon.data.remote.model.move;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoveApiResponse {

    @SerializedName("move")
    @Expose
    private Move move;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

}

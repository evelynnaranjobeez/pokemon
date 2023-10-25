package com.example.pokemon.data.local.converter;

import androidx.room.TypeConverter;

import com.example.pokemon.data.remote.model.move.MoveApiResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MoveResponseTypeConverter {

    @TypeConverter
    public List<MoveApiResponse> fromString(String value) {
        Type listType = new TypeToken<List<MoveApiResponse>>() {
        }.getType();
        List<MoveApiResponse> moves = new Gson().fromJson(value, listType);
        return moves;
    }

    @TypeConverter
    public String fromList(List<MoveApiResponse> moves) {
        return new Gson().toJson(moves);
    }
}

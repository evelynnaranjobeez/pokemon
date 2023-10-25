package com.example.pokemon.data.local.converter;

import androidx.room.TypeConverter;

import com.example.pokemon.data.remote.model.ability.AbilityApiResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AbilityResponseTypeConverter {

    @TypeConverter
    public List<AbilityApiResponse> fromString(String value) {
        Type listType = new TypeToken<List<AbilityApiResponse>>() {
        }.getType();
        List<AbilityApiResponse> abilities = new Gson().fromJson(value, listType);
        return abilities;
    }

    @TypeConverter
    public String fromList(List<AbilityApiResponse> abilities) {
        return new Gson().toJson(abilities);
    }
}

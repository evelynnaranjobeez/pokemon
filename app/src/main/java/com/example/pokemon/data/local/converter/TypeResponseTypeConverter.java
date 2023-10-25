package com.example.pokemon.data.local.converter;

import androidx.room.TypeConverter;

import com.example.pokemon.data.remote.model.type.TypeApiResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TypeResponseTypeConverter {

    @TypeConverter
    public List<TypeApiResponse> fromString(String value) {
        Type listType = new TypeToken<List<TypeApiResponse>>() {
        }.getType();
        List<TypeApiResponse> types = new Gson().fromJson(value, listType);
        return types;
    }

    @TypeConverter
    public String fromList(List<TypeApiResponse> types) {
        return new Gson().toJson(types);
    }
}

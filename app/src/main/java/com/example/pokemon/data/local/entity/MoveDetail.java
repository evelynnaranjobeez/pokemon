package com.example.pokemon.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.pokemon.data.local.converter.PokemonTypeTypeConverter;
import com.example.pokemon.data.remote.model.type.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "pokemons_moves")
public class MoveDetail {

    @TypeConverters(PokemonTypeTypeConverter.class)
    @SerializedName("type")
    @Expose
    private Type type;

    @SerializedName("power")
    @Expose
    private Integer power;

    @SerializedName("pp")
    @Expose
    private Integer pp;

    @SerializedName("name")
    @Expose
    private String name;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public String getName() {
        String s = this.name.replace("-", " ");
        s = s.toUpperCase();
        return s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }





}

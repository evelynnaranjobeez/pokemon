package com.example.pokemon.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.pokemon.data.local.converter.AbilityResponseTypeConverter;
import com.example.pokemon.data.local.converter.TypeResponseTypeConverter;
import com.example.pokemon.data.remote.model.ability.AbilityApiResponse;
import com.example.pokemon.data.remote.model.move.MoveApiResponse;
import com.example.pokemon.data.remote.model.type.TypeApiResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "pokemons_overview")
public class PokemonOverview {

    @TypeConverters(AbilityResponseTypeConverter.class)
    @SerializedName("abilities")
    @Expose
    private List<AbilityApiResponse> abilities = null;

    @SerializedName("base_experience")
    @Expose
    private Integer baseExperience;

    @SerializedName("height")
    @Expose
    private Integer height;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("location_area_encounters")
    @Expose
    private String locationAreaEncounters;

    @SerializedName("moves")
    @Expose
    private List<MoveApiResponse> moves = null;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("order")
    @Expose
    private Integer order;


    @TypeConverters(TypeResponseTypeConverter.class)
    @SerializedName("types")
    @Expose
    private List<TypeApiResponse> types = null;

//    @TypeConverters(TypeResponseTypeConverter.class)
//    @SerializedName("sprites")
//    @Expose
//    private List<SpriteApiResponse> sprites = null;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    public List<AbilityApiResponse> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityApiResponse> abilities) {
        this.abilities = abilities;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<MoveApiResponse> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveApiResponse> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public List<TypeApiResponse> getTypes() {
        return types;
    }

    public void setTypes(List<TypeApiResponse> types) {
        this.types = types;
    }

//    public List<SpriteApiResponse> getSprites() {
//        return sprites;
//    }
//
//    public void setSprites(List<SpriteApiResponse> sprites) {
//        this.sprites = sprites;
//    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
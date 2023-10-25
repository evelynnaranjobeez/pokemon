package com.example.pokemon.data.remote.model;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.data.remote.model.ability.AbilityApiResponse;
import com.example.pokemon.data.remote.model.type.TypeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetail {

    private List<AbilityApiResponse> abilities = new ArrayList<>(2);
    private Integer baseExperience;
    private Integer height;
    private Integer id;
    private String locationAreaEncounters;
    private List<MoveDetail> moves = null;
    private String name;
    private Integer order;

    private List<TypeApiResponse> types = null;
    //private List<SpriteApiResponse> sprites = null;
    private Integer weight;

    public PokemonDetail(PokemonOverview overview) {
        this.abilities = overview.getAbilities();
        this.baseExperience = overview.getBaseExperience();
        this.height = overview.getHeight();
        this.id = overview.getId();
        this.locationAreaEncounters = overview.getLocationAreaEncounters();
        this.name = overview.getName();
        this.order = overview.getOrder();
        this.types = overview.getTypes();
        //this.sprites = overview.getSprites();
        this.weight = overview.getWeight();
    }

    @BindingAdapter("imageURL")
    public static void setImageUrl(ImageView imageView, int id) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + Integer.toString(id) + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public List<AbilityApiResponse> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityApiResponse> abilities) {
        this.abilities.addAll(abilities);
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

    public List<MoveDetail> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveDetail> moves) {
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
//    public void setSprites(List<SpriteApiResponse> types) {
//        this.sprites = types;
//    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
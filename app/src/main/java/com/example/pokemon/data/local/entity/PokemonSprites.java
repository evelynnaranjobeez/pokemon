package com.example.pokemon.data.local.entity;

public class PokemonSprites {
    private String backDefault;
    private String backShiny;
    private String frontDefault;
    private String frontShiny;
    private String dreamWorldFrontDefault;

    // Constructor
    public PokemonSprites(String backDefault, String backShiny, String frontDefault, String frontShiny, String dreamWorldFrontDefault) {
        this.backDefault = backDefault;
        this.backShiny = backShiny;
        this.frontDefault = frontDefault;
        this.frontShiny = frontShiny;
        this.dreamWorldFrontDefault = dreamWorldFrontDefault;
    }

    // Getters
    public String getBackDefault() {
        return backDefault;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public String getDreamWorldFrontDefault() {
        return dreamWorldFrontDefault;
    }
}

package com.example.pokemon.data.remote.model.sprite;

import com.example.pokemon.data.local.entity.PokemonSprites;

public class SpriteApiResponse {
    private PokemonSprites sprites;

    // Getters y Setters
    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }
}

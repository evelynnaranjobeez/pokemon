package com.example.pokemon.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PokemonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemonOverview(List<PokemonOverview> pokemonOverviews);

    @Query("SELECT * FROM pokemons_overview")
    Flowable<List<PokemonOverview>> getAllPokemonsOverview();


    @Query("SELECT * FROM pokemons_moves where id=:id")
    Single<MoveDetail> getPokemonMoveById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemonMove(MoveDetail move);

    @Query("SELECT * FROM pokemons_overview where id=:id")
    Flowable<PokemonOverview> getPokemonDetailById(int id);

}

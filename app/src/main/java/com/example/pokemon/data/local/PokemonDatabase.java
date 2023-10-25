package com.example.pokemon.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pokemon.data.local.converter.AbilityResponseTypeConverter;
import com.example.pokemon.data.local.converter.MoveResponseTypeConverter;
import com.example.pokemon.data.local.converter.PokemonTypeTypeConverter;
import com.example.pokemon.data.local.converter.TypeResponseTypeConverter;
import com.example.pokemon.data.local.dao.PokemonDAO;
import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;

@Database(entities = {PokemonOverview.class,  MoveDetail.class}, version = 1, exportSchema = false)
@TypeConverters({AbilityResponseTypeConverter.class, MoveResponseTypeConverter.class,
         TypeResponseTypeConverter.class,
        PokemonTypeTypeConverter.class})
public abstract class PokemonDatabase extends RoomDatabase {

    private static PokemonDatabase INSTANCE;

    public static PokemonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static PokemonDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                PokemonDatabase.class, "PokemonDetail.db")
                .allowMainThreadQueries().build();
    }

    public abstract PokemonDAO pokemonDAO();
}

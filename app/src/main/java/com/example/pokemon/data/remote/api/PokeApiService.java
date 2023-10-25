package com.example.pokemon.data.remote.api;

import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.data.remote.model.PokeApiResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokeApiService {

    @GET("pokemon?limit=40")
    Observable<PokeApiResponse> loadPokemons(@Query("offset") int offset);

    @GET
    Observable<PokemonOverview> getPokemonOverview(@Url String url);


    @GET("move/{id}")
    Single<MoveDetail> getMoveById(@Path("id") int id);
//
//    @GET
//    Observable<Species> getSpeciesDetail(@Url String url);

}

package com.example.pokemon.data;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pokemon.data.local.PokemonDatabase;
import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.data.remote.NetworkBoundResource;
import com.example.pokemon.data.remote.Resource;
import com.example.pokemon.data.remote.api.PokeApiService;
import com.example.pokemon.data.remote.api.RetrofitClient;
import com.example.pokemon.data.remote.model.PokeApiResponse;
import com.example.pokemon.data.remote.model.move.MoveApiResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Repository {

    private static Repository repository;
    private PokeApiService webService;
    private PokemonDatabase db;
    private int numFetched;

    private Repository(Context context, int stored) {
        this.webService = RetrofitClient.getRetrofitClient(PokeApiService.class);
        this.db = PokemonDatabase.getInstance(context);
        this.numFetched = stored;
    }

    public static Repository getInstance(Context context, int stored) {
        if (repository == null) {
            repository = new Repository(context, stored);
        }
        return repository;
    }

    public static Repository getInstance(Context context) {
        return repository;
    }

    public Observable<Resource<List<PokemonOverview>>> getAllPokemon(int offset) {
        return new NetworkBoundResource<List<PokemonOverview>, PokeApiResponse>() {

            @Override
            protected void saveCallResult(@NonNull PokeApiResponse item) {
                Observable.fromIterable(item.getResults())
                        .concatMap(pokemon -> webService.getPokemonOverview(pokemon.getUrl()))
                        .toList()
                        .subscribe(list -> {
                                    db.pokemonDAO().insertPokemonOverview(list);
                                    numFetched += list.size();
                                },
                                throwable -> Log.e(TAG, throwable.getMessage(), throwable))
                        .dispose();
            }

            @Override
            protected boolean shouldFetch() {
                return numFetched == 0 || numFetched <= offset;
            }

            @NonNull
            @Override
            protected Flowable<List<PokemonOverview>> loadFromDb() {
                return db.pokemonDAO().getAllPokemonsOverview();
            }

            @NonNull
            @Override
            protected Observable<Resource<PokeApiResponse>> createCall() {
                return webService.loadPokemons(offset)
                        .flatMap(pokeApiResponse -> Observable.just(pokeApiResponse == null
                                ? Resource.error("", new PokeApiResponse())
                                : Resource.success(pokeApiResponse)));
            }
        }.getAsObservable();
    }

    public Observable<MoveDetail> getPokemonMoveFromDb(int id) {
        return db.pokemonDAO().getPokemonMoveById(id)
                .onErrorResumeNext(t -> getPokemonMoveFromApi(id))
                .retry()
                .toObservable();
    }

    public Single<MoveDetail> getPokemonMoveFromApi(int id) {
        return webService.getMoveById(id)
                .doAfterSuccess(move -> db.pokemonDAO().insertPokemonMove(move));
    }

    public Observable<Resource<PokemonOverview>> getPokemonDetail(int id) {
        return db.pokemonDAO().getPokemonDetailById(id)
                .toObservable()
                .map(Resource::success)
                .take(1);
    }

    public Observable<List<MoveDetail>> getMoveDetail(List<MoveApiResponse> moves) {
        return Observable.fromIterable(moves)
                .concatMap(move -> {
                    int id = Integer.parseInt(move.getMove().getUrl().substring(30).replace("/", ""));
                    return getPokemonMoveFromDb(id);
                })
                .toList()
                .toObservable();
    }
}

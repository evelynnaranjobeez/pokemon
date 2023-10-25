package com.example.pokemon.viewmodel;

import android.content.Context;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.data.Repository;
import com.example.pokemon.data.local.entity.MoveDetail;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.data.remote.Resource;
import com.example.pokemon.data.remote.model.PokemonDetail;
import com.example.pokemon.data.remote.model.move.MoveApiResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PokemonDetailViewModel extends ViewModel {
    private Repository repo;
    private MutableLiveData<Resource<PokemonOverview>> pokemonData;
    private MutableLiveData<List<MoveDetail>> moveData;
    private MediatorLiveData<Resource<PokemonDetail>> pokemonDetail;

    public void init(Context context) {
        if (pokemonDetail != null) {
            return;
        }
        repo = Repository.getInstance(context);
        pokemonData = new MutableLiveData<>();
        moveData = new MutableLiveData<>();
        pokemonDetail = new MediatorLiveData<>();
    }

    public void getPokemonDetail(int id) {
        pokemonDetail.removeSource(getPokemonData());
        pokemonDetail.removeSource(getMoveData());
        getPokemonData(id);
        pokemonDetail.addSource(getPokemonData(), res -> {

            getMoveDetails(res.data.getMoves());
            PokemonDetail detail;
            if (res != null && res.isLoaded()) {
                detail = new PokemonDetail(res.data);
                pokemonDetail.setValue(Resource.success(detail));
            }
        });



        pokemonDetail.addSource(getMoveData(), res -> {
            PokemonDetail detail = pokemonDetail.getValue().data;
            if (res != null) {
                detail.setMoves(res);
                pokemonDetail.setValue(Resource.success(detail));
            }
        });
    }

    private void getPokemonData(int id) {
        repo.getPokemonDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> getPokemonData().postValue(res));
    }


    private void getMoveDetails(List<MoveApiResponse> moves) {
        repo.getMoveDetail(moves)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moveDetails -> getMoveData().postValue(moveDetails));
    }



    public MutableLiveData<Resource<PokemonOverview>> getPokemonData() {
        return pokemonData;
    }

    public MutableLiveData<List<MoveDetail>> getMoveData() {
        return moveData;
    }

    public MediatorLiveData<Resource<PokemonDetail>> getPokemon() {
        return pokemonDetail;
    }
}

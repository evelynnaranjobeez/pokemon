package com.example.pokemon.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.R;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.view.adapter.PokemonListAdapter;
import com.example.pokemon.viewmodel.PokemonListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonListAdapter.RecyclerViewClickListener {

    LinearLayoutManager layoutManager;
    ProgressBar pb;
    PokemonListAdapter adapter;
    PokemonListViewModel vm;
    List<PokemonOverview> pokemonOverviews = new ArrayList<>();
    int pastVisibleItems, visibleItemCount, totalItemCount, offset, stored;
    private RecyclerView rv;
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
        int initVal = sharedPreferences.getInt("stored", 0);
        rv = findViewById(R.id.rv);
        pb = findViewById(R.id.progressBar);
        pokemonOverviews = new ArrayList<>();
        initView();
        vm = ViewModelProviders.of(this).get(PokemonListViewModel.class);
        vm.init(this, initVal);
        offset = 0;
        vm.loadPokemonFromApi(offset);
        vm.getPokemons().observe(this, resource -> {
            if (resource.isLoading()) {
                pb.setVisibility(View.VISIBLE);
            } else if (resource.isLoaded() && !resource.data.isEmpty()) {
                pb.setVisibility(View.GONE);
                pokemonOverviews.clear();
                pokemonOverviews.addAll(resource.data);
                stored = resource.data.size();
                loading = true;
                setupRV();
            }
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //TODO: fix pagination
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    offset = totalItemCount;
                    if (loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            vm.loadPokemonFromApi(offset);
                        }
                    }
                }
            }
        });
    }

    private void initView() {

      layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);

    }

    private void setupRV() {
        if (adapter == null) {
            adapter = new PokemonListAdapter(pokemonOverviews, this, this);
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        int message = pos + 1;
        intent.putExtra("pokemon_id", message);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.onStop();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("stored", stored);
        editor.commit();
    }
}

package com.example.pokemon.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pokemon.R;
import com.example.pokemon.data.remote.model.PokemonDetail;
import com.example.pokemon.databinding.PokemonBaseFragmentBinding;
import com.example.pokemon.viewmodel.PokemonDetailViewModel;

public class PokemonDetailBaseFragment extends Fragment {

    private PokemonBaseFragmentBinding binding;
    private PokemonDetailViewModel vm;
    private PokemonDetail pokemonDetail;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProviders.of(getActivity()).get(PokemonDetailViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_base_fragment, container, false);
        binding.setLifecycleOwner(this.getActivity());
        vm.getPokemon().observe(getViewLifecycleOwner(), pokemonResource -> {
            if (pokemonResource.isLoaded()) {
                pokemonDetail = pokemonResource.data;
                if (pokemonDetail.getTypes().size() > 1) {
                    String type0 = pokemonDetail.getTypes().get(0).getType().getName();
                    String type1 = pokemonDetail.getTypes().get(1).getType().getName();
                    binding.detailType1.setText(type0);
                    binding.detailType2.setText(type1);
                } else {
                    String type0 = pokemonDetail.getTypes().get(0).getType().getName();
                    binding.detailType1.setText(type0);
                    binding.detailType2.setVisibility(View.GONE);
                }

                binding.detailAbility1.setText(pokemonDetail.getAbilities().get(0).getAbility().getName());
                if (pokemonDetail.getAbilities().size() > 1) {
                    binding.detailAbility2.setText(pokemonDetail.getAbilities().get(1).getAbility().getName());
                } else {
                    binding.detailAbility2.setVisibility(View.GONE);
                }
                binding.setPokemonDetail(pokemonDetail);

            }
        });
        return binding.getRoot();
    }
}

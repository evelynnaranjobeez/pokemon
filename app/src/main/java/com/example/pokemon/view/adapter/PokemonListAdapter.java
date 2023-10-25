package com.example.pokemon.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemon.R;
import com.example.pokemon.data.local.entity.PokemonOverview;
import com.example.pokemon.data.remote.model.type.TypeApiResponse;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.pokemonEntryViewHolder> {
    private final List<PokemonOverview> pokemonOverviews;
    private RecyclerViewClickListener listener;
    private Context context;

    public PokemonListAdapter(List<PokemonOverview> pokemonOverviews, Context context, RecyclerViewClickListener listener) {
        this.listener = listener;
        this.pokemonOverviews = pokemonOverviews;
        this.context = context;
    }

    @NonNull
    @Override
    public pokemonEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_list_item, parent, false);
        return new pokemonEntryViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull pokemonEntryViewHolder holder, int position) {
        PokemonOverview result = pokemonOverviews.get(position);
        holder.name.setText(result.getName().toUpperCase());
        holder.id.setText(Integer.toString(result.getId()));
        List<TypeApiResponse> types = pokemonOverviews.get(position).getTypes();

        String type = types.get(types.size() - 1).getType().getName();


        int id = result.getId();
        Glide.with(context)
                .load(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png")

                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pokemonOverviews.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(int pos);
    }

    public class pokemonEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView id;
        public ImageView image;
        public CardView cardPokemon;

        RecyclerViewClickListener recyclerViewClickListener;


        public pokemonEntryViewHolder(View view, RecyclerViewClickListener recyclerViewClickListener) {
            super(view);
            name = view.findViewById(R.id.poke_name);
            id = view.findViewById(R.id.id);
            image = view.findViewById(R.id.poke_img);
            cardPokemon = view.findViewById(R.id.card_pokemon);
            this.recyclerViewClickListener = recyclerViewClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(getAdapterPosition());
        }
    }

}

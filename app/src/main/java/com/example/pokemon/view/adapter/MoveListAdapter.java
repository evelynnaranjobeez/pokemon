package com.example.pokemon.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.R;
import com.example.pokemon.data.local.entity.MoveDetail;

import java.util.List;

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.MoveViewHolder> {

    Context context;
    private List<MoveDetail> moves;

    public MoveListAdapter(List<MoveDetail> moves, Context context) {
        this.moves = moves;
        this.context = context;
    }

    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.move_list_item, parent, false);
        return new MoveListAdapter.MoveViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
        MoveDetail move = moves.get(position);
        holder.moveName.setText(move.getName());
    }

    @Override
    public int getItemCount() {
        if (moves != null)
            return moves.size();
        else return 0;
    }

    public class MoveViewHolder extends RecyclerView.ViewHolder {
        public TextView moveName;


        public MoveViewHolder(@NonNull View itemView) {
            super(itemView);
            moveName = itemView.findViewById(R.id.move_name);
        }
    }
}

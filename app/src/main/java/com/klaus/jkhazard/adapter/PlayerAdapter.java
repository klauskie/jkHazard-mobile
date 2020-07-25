package com.klaus.jkhazard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.model.Player;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    Context mContext;
    ArrayList<Player> mPlayers;

    public PlayerAdapter(Context context, ArrayList<Player> players) {
        mContext = context;
        mPlayers = players;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_player, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        holder.bind(mPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    /* Holder */
    public class PlayerHolder extends RecyclerView.ViewHolder {

        TextView mPlayerName;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.item_list_player_name);
        }

        void bind(Player player) {
            mPlayerName.setText(player.getUsername());
        }
    }
}

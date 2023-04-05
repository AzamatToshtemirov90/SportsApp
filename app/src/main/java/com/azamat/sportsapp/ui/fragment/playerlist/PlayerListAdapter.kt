package com.azamat.sportsapp.ui.fragment.playerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamat.sportsapp.databinding.ItemListPlayerBinding
import com.azamat.sportsapp.model.remote.response.Player
import com.squareup.picasso.Picasso


class PlayerListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    var items = listOf<Player>()

    lateinit var binding: ItemListPlayerBinding

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = items[position]
        binding.tvName.text = player.strPlayer
        if (player.strCutout != null) {
            Picasso.get().load(player.strCutout).into(binding.imgItem)
        } else if (player.strThumb != null) {
            Picasso.get().load(player.strThumb).into(binding.imgItem)
        } else if (player.strRender != null) {
            Picasso.get().load(player.strRender).into(binding.imgItem)
        }
        binding.tvNation.text = player.strNationality

        holder.itemView.setOnClickListener {
            listener.onItemClick(player)
        }
    }

    class OnItemClickListener(val listener: (player: Player) -> Unit) {
        fun onItemClick(player: Player) = listener(player)
    }

    class ViewHolder(binding: ItemListPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)
}
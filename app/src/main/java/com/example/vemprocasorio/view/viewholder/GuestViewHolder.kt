package com.example.vemprocasorio.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.vemprocasorio.databinding.RowGuestBinding
import com.example.vemprocasorio.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name
    }
}
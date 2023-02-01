package com.example.vemprocasorio.view.viewholder


import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.vemprocasorio.databinding.RowGuestBinding
import com.example.vemprocasorio.model.GuestModel
import com.example.vemprocasorio.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener{
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        listener.onDelete(guest.id)
                    }

                })
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}
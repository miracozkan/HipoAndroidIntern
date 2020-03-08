package com.miracozkan.hipoandroidintern.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.databinding.ItemAdapterMemberBinding


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 18:13          │
//└─────────────────────────────┘

class MembersViewHolder(private val binding: ItemAdapterMemberBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(member: Member, onClickListener: (Member) -> Unit) {
        binding.txtMemberName.text = member.name
        itemView.setOnClickListener {
            onClickListener(member)
        }
    }

}
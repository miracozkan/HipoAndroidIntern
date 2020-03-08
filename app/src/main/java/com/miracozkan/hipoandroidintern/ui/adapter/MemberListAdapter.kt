package com.miracozkan.hipoandroidintern.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.databinding.ItemAdapterMemberBinding
import com.miracozkan.hipoandroidintern.util.GenericDiffUtil


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 18:10          │
//└─────────────────────────────┘

class MemberListAdapter(
    private val onItemClickListener: (Member) -> Unit
) : ListAdapter<Member, MembersViewHolder>(GenericDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        val binding = ItemAdapterMemberBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MembersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}
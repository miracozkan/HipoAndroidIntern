package com.miracozkan.hipoandroidintern.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.databinding.ItemAdapterMemberBinding
import java.util.*
import kotlin.collections.ArrayList


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 18:10          │
//└─────────────────────────────┘

class MemberListAdapter(
    private var memberList: ArrayList<Member>? = null,
    private val onItemClickListener: (Member) -> Unit
) : RecyclerView.Adapter<MembersViewHolder>(), Filterable {

    private var filteredMemberList = ArrayList<Member>()

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
        holder.bind(filteredMemberList[position], onItemClickListener)
    }

    override fun getItemCount(): Int =
        filteredMemberList.size


    fun setNewMemberList(memberList: ArrayList<Member>) {
        this.memberList = memberList.also {
            filteredMemberList = it
            notifyDataSetChanged()
        }
    }

    fun addNewMember(member: Member) {
        memberList?.let {
            it.add(member)
            filteredMemberList = it
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                constraint?.let { searchText ->
                    filteredMemberList = if (searchText.isEmpty() or searchText.isBlank()) {
                        memberList
                    } else {
                        val resultList = arrayListOf<Member>()
                        for (row in memberList.orEmpty()) {
                            if (row.name.toLowerCase(Locale.getDefault()).contains(searchText)) {
                                resultList.add(row)
                            }
                        }
                        resultList
                    }.orEmpty() as java.util.ArrayList<Member>
                }
                return FilterResults().apply {
                    values = filteredMemberList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredMemberList = results?.values as ArrayList<Member>
                notifyDataSetChanged()
            }
        }
    }
}
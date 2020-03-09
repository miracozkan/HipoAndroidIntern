package com.miracozkan.hipoandroidintern.ui.member_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miracozkan.hipoandroidintern.R
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.databinding.FragmentMemberSearchBinding
import com.miracozkan.hipoandroidintern.di.ViewModelFactory
import com.miracozkan.hipoandroidintern.ui.adapter.MemberListAdapter
import com.miracozkan.hipoandroidintern.util.*
import com.miracozkan.hipoandroidintern.util.Status.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MemberSearchFragment : DaggerFragment(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var memberSearchViewModel: MemberSearchViewModel

    private lateinit var binding: FragmentMemberSearchBinding
    private lateinit var adapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMemberSearchBinding.inflate(layoutInflater)

        memberSearchViewModel = injectViewModel(viewModelFactory)

        adapter = MemberListAdapter {
            showSnackBar(it.name)
        }.also { memberAdapter ->
            binding.recycMemberList.adapter = memberAdapter
        }

        binding.srcMemberName.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memberSearchViewModel.teamMembers.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                SUCCESS -> {
                    binding.prgMemberList.hide()
                    adapter.setNewMemberList(result.data.orEmpty() as ArrayList<Member>)
                }
                ERROR -> {
                    showSnackBar(result.message ?: getString(R.string.error_null))
                    binding.prgMemberList.hide()
                }
                LOADING -> {
                    binding.prgMemberList.show()
                }
            }
        })

        binding.btnAddNewMember.setOnClickListener {
            adapter.addNewMember(generateNewMember())
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText ?: " ")
        return false
    }
}

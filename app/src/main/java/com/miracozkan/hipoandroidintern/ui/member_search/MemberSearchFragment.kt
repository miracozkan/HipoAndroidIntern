package com.miracozkan.hipoandroidintern.ui.member_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miracozkan.hipoandroidintern.R
import com.miracozkan.hipoandroidintern.databinding.FragmentMemberSearchBinding
import com.miracozkan.hipoandroidintern.di.NetworkState.*
import com.miracozkan.hipoandroidintern.di.ViewModelFactory
import com.miracozkan.hipoandroidintern.ui.adapter.MemberListAdapter
import com.miracozkan.hipoandroidintern.util.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memberSearchViewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMemberSearchBinding.inflate(layoutInflater)

        memberSearchViewModel.networkState.observe(viewLifecycleOwner, Observer {
            when (it) {
                CONNECTED -> {
                    showSnackBar(getString(R.string.you_are_online))
                }
                DISCONNECTED -> {
                    showSnackBar(getString(R.string.you_are_offline))
                }
                CONNECTION_LOST -> {
                    showSnackBar(getString(R.string.lost_connection))
                }
                null -> {
                    Unit
                }
            }
        })

        adapter = MemberListAdapter {
            showSnackBar(it.name)
        }.also { memberAdapter ->
            binding.recycMemberList.adapter = memberAdapter
        }

        binding.btnAddNewMember.setOnClickListener {
            memberSearchViewModel.addNewMember(generateNewMember())
        }

        binding.srcMemberName.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMemberListObserver()
    }

    private fun initMemberListObserver() {
        memberSearchViewModel.teamMembers.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    binding.prgBarMemberList.hide()
                    adapter.setNewMembers(result.data.orEmpty())
                }
                Status.ERROR -> {
                    result.message?.let {
                        showSnackBar(result.message)
                    }
                    binding.prgBarMemberList.hide()
                }
                Status.LOADING -> {
                    binding.prgBarMemberList.show()
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        memberSearchViewModel.searchList(newText ?: SPACE)
        return false
    }

}

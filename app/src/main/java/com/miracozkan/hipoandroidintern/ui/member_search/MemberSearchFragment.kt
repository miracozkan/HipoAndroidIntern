package com.miracozkan.hipoandroidintern.ui.member_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miracozkan.hipoandroidintern.databinding.FragmentMemberSearchBinding
import com.miracozkan.hipoandroidintern.di.ViewModelFactory
import com.miracozkan.hipoandroidintern.ui.adapter.MemberListAdapter
import com.miracozkan.hipoandroidintern.util.Status.*
import com.miracozkan.hipoandroidintern.util.hide
import com.miracozkan.hipoandroidintern.util.injectViewModel
import com.miracozkan.hipoandroidintern.util.show
import com.miracozkan.hipoandroidintern.util.showSnackBar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MemberSearchFragment : DaggerFragment() {

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
        }.also {
            binding.recycMemberList.adapter = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memberSearchViewModel.teamMembers.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                SUCCESS -> {
                    binding.prgMemberList.hide()
                    adapter.submitList(result.data)
                }
                ERROR -> {
                    showSnackBar(result.message ?: "Something went wrong")
                    binding.prgMemberList.hide()
                }
                LOADING -> {
                    binding.prgMemberList.show()
                }
            }
        })

    }
}

package com.example.doterb_belajar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doterb_belajar.R
import com.example.doterb_belajar.databinding.FragmentHomeBinding
import com.example.mymoviddb.core.utils.preference.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = BelajarRecyclerViewAdapter()

        if (viewModel.getAuthState() == LoginState.AS_USER.stateId) {
            viewModel.getBelajarList()
        } else {
            findNavController().navigate(R.id.loginFragment)
        }

        viewModel.belajarLis.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        return binding.root
    }
}
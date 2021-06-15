package com.example.doterb_belajar.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doterb_belajar.databinding.FragmentLoginBinding
import com.example.doterb_belajar.model.EventObserver
import com.example.doterb_belajar.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewmodel by viewModels<LoginviewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.loginButton.setOnClickListener {
            viewmodel.login(
                binding.usernameInputText.text.toString(),
                binding.passwordInputText.text.toString()
            )
        }

        observeLoginResult()
        observeFieldValidation()

        return binding.root
    }

    private fun observeLoginResult() {
        viewmodel.loginResult.observe(viewLifecycleOwner) {
            if (it is Result.Success) {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeFieldValidation() {
        viewmodel.usernameError.observe(viewLifecycleOwner, EventObserver {

        })

        viewmodel.passswordError.observe(viewLifecycleOwner, EventObserver {

        })
    }
}
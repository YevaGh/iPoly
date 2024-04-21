package com.example.ipoly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentLoginBinding
import com.example.ipoly.viewmodels.AuthUserViewModel
import com.example.ipoly.viewmodels.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel by viewModels<LoginViewModel>()
    private val authViewModel by viewModels<AuthUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                var result = ""
                loginViewModel.authenticate(email, password) {
                    result = it
                    if (result.isNotEmpty() && result!="0") {
                        authViewModel.cacheSessionIdAndEmail(result,email)
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    } else {
                        Toast.makeText(requireContext(), "Մուտքանունը կամ գաղտնաբառը սխալ է", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        binding.forgetPasswordTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
        }
        return binding.root
    }
}
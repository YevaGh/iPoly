package com.example.ipoly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.viewmodels.AuthUserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private val authViewModel by viewModels<AuthUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)
            if(authViewModel.getSessionId().isNotEmpty() && authViewModel.getSessionId()!="null"){
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}
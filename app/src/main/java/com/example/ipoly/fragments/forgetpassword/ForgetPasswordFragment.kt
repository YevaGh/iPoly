package com.example.ipoly.fragments.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentDocumentBinding
import com.example.ipoly.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment : Fragment() {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sendButton.setOnClickListener {
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_codeFragment)

        }
        super.onViewCreated(view, savedInstanceState)
    }
}
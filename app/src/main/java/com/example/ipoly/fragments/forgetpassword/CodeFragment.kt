package com.example.ipoly.fragments.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentCodeBinding
import com.example.ipoly.databinding.FragmentForgetPasswordBinding


class CodeFragment : Fragment() {
    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.materialButton.setOnClickListener {
            findNavController().navigate(R.id.action_codeFragment_to_createPasswordFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
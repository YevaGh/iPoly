package com.example.ipoly.fragments.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentCreatePasswordBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CreatePasswordFragment : Fragment() {
    private var _binding: FragmentCreatePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.materialButton.setOnClickListener {
            val bottomSheetFragment = CongratsBottomSheetFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            viewLifecycleOwner.lifecycleScope.launch {
                delay(2000)
                findNavController().navigate(R.id.action_createPasswordFragment_to_loginFragment2)
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }
}
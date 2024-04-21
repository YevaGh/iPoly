package com.example.ipoly.fragments.mainfragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentAbsenceGpaBinding
import com.example.ipoly.viewmodels.AbsenceGpaViewModel
import com.example.ipoly.viewmodels.AuthUserViewModel
import kotlinx.coroutines.launch


class AbsenceGpaFragment : Fragment() {
    private var _binding: FragmentAbsenceGpaBinding? = null
    private val binding get() = _binding!!

    private val absenceGpaVM by viewModels<AbsenceGpaViewModel>()
    private val authVM by viewModels<AuthUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsenceGpaBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launch {

            val userId = authVM.getUserId()
            val absGpa = absenceGpaVM.getAbsenceAndGpa(userId)
            val absence = absGpa.absenceCount
            val gpa = absGpa.gpa
            with(binding) {
                progressBar.progress = if (absence >= 60) 0 else absence
                absenceTv.text = absence.toString()
                absence2Tv.text = absence.toString()
                gpaTv.text = gpa.toString()
            }
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle) as GradientDrawable

            if(absence > 50){
                drawable.setColor(Color.parseColor("#FFD6332D"))
                drawable.setStroke(1, Color.parseColor("#FFFC523E"))
            } else if(absence>40){
                drawable.setColor(Color.parseColor("#FFFCBF3E"))
                drawable.setStroke(1, Color.parseColor("#FFFCBF3E"))
            }
            binding.absenceImg.background = drawable

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
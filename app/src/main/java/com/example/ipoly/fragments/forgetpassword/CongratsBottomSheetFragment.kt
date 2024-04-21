package com.example.ipoly.fragments.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ipoly.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CongratsBottomSheetFragment : BottomSheetDialogFragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_congrats_bottom_sheet, container, false)
    }


}
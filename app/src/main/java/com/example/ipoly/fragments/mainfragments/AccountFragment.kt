package com.example.ipoly.fragments.mainfragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentAccountBinding
import com.example.ipoly.viewmodels.AccountViewModel
import com.example.ipoly.viewmodels.AuthUserViewModel
import kotlinx.coroutines.launch


class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val accountVM by viewModels<AccountViewModel>()
    private val authVM by viewModels<AuthUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        init()
        listeners()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        viewLifecycleOwner.lifecycleScope.launch {
            val userId = authVM.getUserId()
            val userData = accountVM.getData(userId)
            with(binding) {
                nameTv.text = "${userData.name} ${userData.lastname}"
                bloodTv.text = userData.bloodGroup
                mobileTv.text = userData.mobile
                sexTv.text = userData.sex
                mailTv.text = authVM.getEmail()
            }
        }
    }

    private fun listeners()
    {
        with(binding) {
            logOutButton.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(requireContext())

                alertDialogBuilder.setTitle("")
                    .setMessage("Արդյոք ուզում ե՞ք դուրս գալ")
                    .setPositiveButton("Այո") { _, _ ->
                        val parentNavController = parentFragment?.findNavController()
                        parentNavController?.navigate(R.id.action_mainFragment_to_loginFragment)

                    }
                    .setNegativeButton("Չեղարկել") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.ipoly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentMainBinding
import com.example.ipoly.fragments.mainfragments.AbsenceGpaFragment
import com.example.ipoly.fragments.mainfragments.AccountFragment
import com.example.ipoly.fragments.mainfragments.DocumentFragment

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val absenceGpaFragment = AbsenceGpaFragment()
    private val documentFragment = DocumentFragment()
    private val accountFragment = AccountFragment()
    private lateinit var activeFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.menuBottom.itemIconTintList = null
        showFragment(absenceGpaFragment)
        listeners()

        return binding.root
    }

    private fun listeners() {
        binding.menuBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chart -> {
                    showFragment(absenceGpaFragment)
                    true
                }

                R.id.document -> {
                    showFragment(documentFragment)
                    true
                }

                R.id.account -> {
                    showFragment(accountFragment)
                    true
                }
                else->false
            }
        }
    }
    private fun showFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()

        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fragment_holder, fragment)
        }

        listOf(absenceGpaFragment, documentFragment, accountFragment)
            .filterNot { it == fragment }
            .forEach { transaction.hide(it) }


        transaction.commit()

        activeFragment = fragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.onefin.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onefin.databinding.FragmentFirstOnboardingBinding


class FirstOnboardingFragment : Fragment() {

    private var _binding: FragmentFirstOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFirstOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        initClickListeners()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

//    private fun initClickListeners() {
//        binding.firstOnboardingNextButton.setOnClickListener {
//            parentFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container, SecondOnboardingFragment(), "SecondOnboarding")
//            }.commit()
//        }
//    }


}
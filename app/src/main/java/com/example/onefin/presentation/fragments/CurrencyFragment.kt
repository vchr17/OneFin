package com.example.onefin.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onefin.databinding.FragmentCurrencyBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.adapters.CurrencyAdapter
import com.example.onefin.presentation.view_models.CurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CurrencyViewModel>()
    private val list = mutableListOf<Money>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    private fun initAdapter() {
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.setHasFixedSize(true)
        binding.rcView.adapter = CurrencyAdapter(list, viewModel)

    }

    private fun initObserver(){
        if (list.isEmpty()) {
            viewModel.liveData.observe(viewLifecycleOwner, Observer {list.addAll(it)})
        }
    }

    private fun init() {
        if(list.isEmpty()){
            viewModel.init().invokeOnCompletion {
                activity?.runOnUiThread {
                    initObserver()
                    initAdapter()}
            }
        }
        else initAdapter()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}

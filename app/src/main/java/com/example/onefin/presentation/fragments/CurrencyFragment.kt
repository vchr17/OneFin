package com.example.onefin.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onefin.R
import com.example.onefin.databinding.FragmentCurrencyBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.activities.MainActivity
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
        initObserver()
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
        viewModel.shareData.observe(viewLifecycleOwner, Observer{ text ->
            if(text.isNotBlank()){
               share(text)
                viewModel.clearShareText()
            }
        })
    }

    private fun share(text: String){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else throw IllegalArgumentException(getString(R.string.unuseable_intent))
    }

    private fun init() {
            if (list.isEmpty()) {
                viewModel.init().invokeOnCompletion {
                    activity?.runOnUiThread {
                        initAdapter()
                    }
                }
            } else initAdapter()

        }




    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}

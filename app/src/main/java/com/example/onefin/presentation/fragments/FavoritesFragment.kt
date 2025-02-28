package com.example.onefin.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onefin.R
import com.example.onefin.databinding.FragmentFavoritesBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.adapters.FavouritesAdapter
import com.example.onefin.presentation.view_models.FavouritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {
    private val viewModel by viewModel<FavouritesViewModel>()
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val list = mutableListOf<Money>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun initAdapter() {
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.setHasFixedSize(true)
        val adapter = FavouritesAdapter(list, viewModel)
        binding.rcView.adapter = adapter

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

    private fun share(text: String){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else throw IllegalArgumentException(getString(R.string.unuseable_intent))
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
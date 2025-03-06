package com.example.onefin.presentation.fragments

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.onefin.databinding.FragmentExchangeBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.view_models.ExchangeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExchangeFragment : Fragment() {

    private val viewModel by viewModel<ExchangeViewModel>()
    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!
    private val list = mutableListOf<Money>()
    private var mainSpinnerValue: String? = null
    private var secondarySpinnerValue: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        viewModel.init().invokeOnCompletion {
            activity?.runOnUiThread {
                initObservers()
                initAdapter()
                initExchange()
            }
        }
    }

    private fun initExchange() {
        binding.valueMainEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val df = DecimalFormat("#.##")
                val text = binding.valueMainEditText.text.toString()
                if (text.isBlank()) {
                    binding.valueMainEditText.setText("0")
                } else {
                    val value = viewModel.exchange(
                        mainSpinnerValue!!,
                        binding.valueMainEditText.text.toString().toDouble(),
                        secondarySpinnerValue!!
                    )
                    binding.valueText.text = df.format(value)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun initObservers() {
        if (list.isEmpty()) {
            viewModel.liveData.observe(viewLifecycleOwner, Observer { list.addAll(it) })
        }
    }

    private fun initAdapter() {
        val items = viewModel.getNames(list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.valueMainSpinner.adapter = adapter
        binding.valueSecondarySpinner.adapter = adapter
        binding.valueMainSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mainSpinnerValue = p0!!.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mainSpinnerValue = null
                }

            }
        binding.valueSecondarySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    secondarySpinnerValue = p0!!.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    secondarySpinnerValue = null
                }

            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
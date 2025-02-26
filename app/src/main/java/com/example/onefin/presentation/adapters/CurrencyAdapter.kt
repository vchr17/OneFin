package com.example.onefin.presentation.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onefin.databinding.RcValueBinding
import com.example.onefin.domain.model.Money

class CurrencyAdapter(moneyList: MutableList<Money>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    private var list = moneyList

    class CurrencyHolder(binding: RcValueBinding) : ViewHolder(binding.root) {
        private val df = DecimalFormat("#.##")
        private val name = binding.valueName
        private val value = binding.valueCost
        fun setData(money: Money) {
            name.text = money.name
            if (money.value > 1) {
                value.text = df.format(money.value) + money.name
            } else {
                value.text = df.format(1 / money.value) + "BYN"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = RcValueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.setData(list[position])
    }


}
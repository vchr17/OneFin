package com.example.onefin.presentation.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onefin.R
import com.example.onefin.databinding.RcValueBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.view_models.CurrencyViewModel

class CurrencyAdapter(moneyList: MutableList<Money>, viewModel: CurrencyViewModel) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    private var list = moneyList
    private val vm = viewModel
    private var listener : OnItemClickListener? = null

    class CurrencyHolder(binding: RcValueBinding) : ViewHolder(binding.root) {
        private val name = binding.valueName
        private val value = binding.valueCost
        fun setData(money: Money) {
            val df = DecimalFormat("#.##")
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
        holder.itemView.setOnLongClickListener { view ->
            val menu = PopupMenu(view.context, view)
            menu.inflate(R.menu.pop_up_menu)
            menu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.add_to_favourites -> {
                        vm.setFavorite(list[position].name)
                        true
                    }

                    R.id.share_button -> {

                        true
                    }

                    else -> false
                }
            }
            menu.show()
            true
        }

    }
}


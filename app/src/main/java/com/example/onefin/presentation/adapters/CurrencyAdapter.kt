package com.example.onefin.presentation.adapters

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.onefin.R
import com.example.onefin.databinding.RcValueBinding
import com.example.onefin.domain.model.Money
import com.example.onefin.presentation.view_models.CurrencyViewModel

class CurrencyAdapter(moneyList: MutableList<Money>, viewModel: CurrencyViewModel) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    private var list = moneyList
    private val vm = viewModel

    class CurrencyHolder(binding: RcValueBinding) : ViewHolder(binding.root) {
        private val name = binding.valueName
        private val value = binding.valueCost
        private val flag = binding.flagView
        private val hint = binding.hintText
        fun setData(money: Money) {
            val df = DecimalFormat("#.##")
            val flagUri : String
            if(money.name != "EUR"){
                val flagName = money.name.substring(0,2).lowercase()
                flagUri = "flags/w580/$flagName.webp"
            }else flagUri = "org/w580/eu.webp"
            val uri = "https://www.flagistrany.ru/data/$flagUri"
            Glide.with(itemView.context).load(uri).into(flag)
            name.text = money.name
            if (money.value > 1) {
                value.text = df.format(money.value) + money.name
                hint.hint = itemView.context.getString(R.string.Currency_for_byn)
            } else {
                value.text = df.format(1 / money.value) + "BYN"
                hint.hint = itemView.context.getString(R.string.currency_for_foreign, money.name)
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
                        vm.shareText(
                            parseText(
                                list[position].name,
                                list[position].value,
                                view.context
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
            menu.show()
            true
        }

    }

    private fun parseText(name: String, cost: Double, context: Context): String {
        val df = DecimalFormat("#.##")
        if (cost > 1) {
            val value = df.format(cost)
            val text = context.getString(R.string.first_share_format, value, name)
            return text
        } else {
            val value = df.format(1 / cost)
            val text = context.getString(R.string.second_share_format, name, value)
            return text
        }
    }
}


package com.example.onefin.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onefin.domain.model.Money
import com.example.onefin.domain.use_cases.crud.read.GetDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ExchangeViewModel(private val getData: GetDataUseCase) : ViewModel() {
    private val _liveData = MutableLiveData<MutableList<Money>>()
    val liveData: LiveData<MutableList<Money>> get() = _liveData
    private val list = mutableListOf<Money>()
    private var byn: Double = 0.00

    fun init() = viewModelScope.async(Dispatchers.IO) {
        getData()
    }

    fun exchange(
        mainSpinnerValue: String,
        mainText: Double,
        secondarySpinnerValue: String,
    ): Double? {

        val moneyValue = list.find { it.name == mainSpinnerValue }
        var value: Double? = 0.00
        when {
            moneyValue!!.value < 1 -> {
                byn =
                    mainText / moneyValue.value
                val secondCurrency = list.find { it.name == secondarySpinnerValue }
                val secondValue = secondCurrency!!.value
                    value = byn * secondValue
            }

            moneyValue.value > 1 && moneyValue.value <10 -> {
                byn =
                    mainText * moneyValue.value
                val secondCurrency = list.find { it.name == secondarySpinnerValue }
                val secondValue = secondCurrency!!.value
                    value = byn * secondValue
            }
            moneyValue.value > 10 -> {
                byn = mainText / moneyValue.value
                val secondCurrency = list.find { it.name == secondarySpinnerValue }
                val secondValue = secondCurrency!!.value
                    value = byn * secondValue
            }

        }
        return value
    }

    private suspend fun getData() {
        if (list.isEmpty()){
        list.addAll(getData.invoke())
        _liveData.postValue(list)
        }
    }

    fun getNames(data: MutableList<Money>): MutableList<String> {
        val list = mutableListOf<String>()
        data.forEach { money ->
            list.add(money.name)
        }
        return list
    }


}
package com.example.onefin.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onefin.domain.model.Money
import com.example.onefin.domain.use_cases.crud.read.GetFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FavouritesViewModel(private val getFavourite : GetFavouritesUseCase ) : ViewModel() {

    private val _liveData = MutableLiveData<MutableList<Money>>()
    val liveData: LiveData<MutableList<Money>> get() = _liveData
    var list = mutableListOf<Money>()

    fun init() = viewModelScope.async(Dispatchers.IO) {
        getFavourites()
    }


    private suspend fun getFavourites() {
        if (list.isEmpty()) {
             list.addAll(getFavourite.invoke())
            _liveData.postValue(list)
        }else{
            val newList = getFavourite.invoke()
            if (list != newList){
                list = newList
                _liveData.postValue(list)
            }
        }
    }
}
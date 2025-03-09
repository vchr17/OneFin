package com.example.onefin.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onefin.domain.model.Money
import com.example.onefin.domain.use_cases.crud.read.GetFavouritesUseCase
import com.example.onefin.domain.use_cases.crud.update.SetFavouriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavouritesViewModel(private val getFavourite : GetFavouritesUseCase,
    private val setFavourite: SetFavouriteStatusUseCase
    ) : ViewModel() {

    private val _liveData = MutableLiveData<MutableList<Money>>()
    private val _shareData = MutableLiveData<String>()
    val liveData: LiveData<MutableList<Money>> get() = _liveData
    val shareData: LiveData<String> get() = _shareData
    var list = mutableListOf<Money>()

    fun init() = viewModelScope.async(Dispatchers.IO) {
        getFavourites()
    }

    fun removeFromFavourites(name: String, ifFavourite: Int) =viewModelScope.launch(Dispatchers.IO){
        setFavourite.invoke(name, ifFavourite)

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

    fun shareText(text: String) {
        _shareData.postValue(text)
    }

    fun clearShareText() {
        _shareData.postValue("")
    }
}
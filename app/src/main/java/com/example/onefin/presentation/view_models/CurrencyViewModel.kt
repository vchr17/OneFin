package com.example.onefin.presentation.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onefin.domain.model.Money
import com.example.onefin.domain.model.Response
import com.example.onefin.domain.use_cases.DataFetch
import com.example.onefin.domain.use_cases.crud.create.AddDataUseCase
import com.example.onefin.domain.use_cases.crud.read.GetDataUseCase
import com.example.onefin.domain.use_cases.crud.update.SetFavouriteStatusUseCase
import com.example.onefin.domain.use_cases.crud.update.UpdateCurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class CurrencyViewModel(
    private val fetchData: DataFetch,
    private val addData: AddDataUseCase,
    private val readData: GetDataUseCase,
    private val setFavourite: SetFavouriteStatusUseCase,
    private val updateCurrency : UpdateCurrencyUseCase
) : ViewModel() {
    private val _liveData = MutableLiveData<MutableList<Money>>()
    private val _shareData = MutableLiveData<String>()
    val liveData: LiveData<MutableList<Money>> get() = _liveData
    val shareData: LiveData<String> get() = _shareData


    fun init() = viewModelScope.async(Dispatchers.IO) {
        /**
         * С помощью isDbEmpty мы проверяем наличие данных в локальной базе данных
         * Если данная функция возвращет true - соответсвенно произошел
         * первый запуск приложения, и база данных пуста
         * Происходит запуск функции fetch(), которая с помощью retrofit делает запрос
         * на удаленный сервер, и возвращает данные в виде класс Response
         * Далее делаем парсинг данных из Response в дата-класс Money, складываем их в
         * MutableList и сохраняем это все в базу данных
         */

        if (isDbEmpty()) {
            val response = async { fetch() }
            val list = parse(response.await())
            addData(list)
            _liveData.postValue(list)
        } else {
            /**
             * ifDataIsFresh сверяет системное время с штампом времени полученным вместе с
             * данными с удаленного сервера. Поставка ведется в UNIX формате, поэтому требуется
             * переформатирование в обычный вид ГОД/МЕСЯЦ/ДАТА
             * Если возвращается true - значит данные свежие(в действующих суток), и повторный запрос на удаленный
             * сервер не требуется, сооветственно происходит экономия огарниченных запросов API
             */
            if (ifDataIsFresh()) {
                val list = async { getData() }
                _liveData.postValue(list.await())
            } else {
                val response = async { fetch() }
                val list = parse(response.await())
                _liveData.postValue(list)
                Log.d("update", "update")
                list.forEach{money->
                    updateCurrency.invoke(money.name, money.stamp, money.value)
                }
                addData(list)
            }
        }
    }


    private suspend fun isDbEmpty(): Boolean {
        val data = readData.invoke()
        Log.d("db", "DbIsEmpty")
        if (data.isEmpty()) {
            return true
        } else return false
    }

    private suspend fun getData(): MutableList<Money> {
        return readData.invoke()
    }

    private suspend fun ifDataIsFresh(): Boolean {
        /*
        Нужно свести все к UTC формата
         */
        val data = readData.invoke()
        val stamp = data[0].stamp * 1000L
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val stampFormated = sdf.format(stamp)
        val currentDate = System.currentTimeMillis()
        val systemDate = sdf.format(currentDate)
        Log.d("dataFresh", "DataFresh")
        if (stampFormated == systemDate) {
            return true
        } else return false
    }

    private suspend fun addData(list: MutableList<Money>) = addData.invoke(list)

    private suspend fun fetch(): Response = fetchData.init()

    private fun parse(response: Response): MutableList<Money> {
        val list = mutableListOf<Money>()
        response.quotes.forEach { (s, d) ->
            val name = s.substring(3)
            val value = d
            val money = Money(response.timestamp, name, value)
            list.add(money)
        }
        return list
    }



fun setFavorite(name: String) = viewModelScope.launch(Dispatchers.IO) {
    setFavourite.invoke(name, 1)
}

fun shareText(text: String) {
    _shareData.postValue(text)
}

fun clearShareText() {
    _shareData.postValue("")
}


}
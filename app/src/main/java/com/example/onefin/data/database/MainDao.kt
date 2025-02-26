package com.example.onefin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.onefin.domain.model.Money

@Dao
interface MainDao {

    @Insert
    fun insertItem(list: MutableList<Money>)

    @Query("UPDATE CurrencyTable SET isFavourite =:ifFavourite WHERE name =:name")
    fun setFavourite(name : String, ifFavourite: Int)

    @Query("SELECT * FROM CurrencyTable")
    fun getData() : MutableList<Money>

    @Query("DELETE FROM CurrencyTable")
    fun deleteData()

    @Query("SELECT * FROM CurrencyTable WHERE isFavourite = 0")
    fun getFavourites() : MutableList<Money>

}
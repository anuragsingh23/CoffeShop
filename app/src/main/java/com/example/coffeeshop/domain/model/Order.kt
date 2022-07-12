package com.example.coffeeshop.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "order")
data class Order(
    val quantityEspresso: String?,
    val sizeEspreeso: String?,
    val quantityCappucciano: String?,
    val sizeCappucciano: String?,
    val quantityLatte: String?,
    val sizeLatte: String?,
    val quantityFrappe: String?,
    val sizeFrappe: String?,
    val note: String? = null,
    val amount: String,
   @PrimaryKey val date: String
){

}



package com.example.coffeeshop.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "order")
data class Order(
    val quantityEspresso: String? = null,
    val sizeEspreeso: String? = null,
    val quantityCappucciano: String? = null,
    val sizeCappucciano: String? = null,
    val quantityLatte: String? = null,
    val sizeLatte: String? = null,
    val quantityFrappe: String? = null,
    val sizeFrappe: String? = null,
    val note: String? = null,
    val amount: String? = null,
   @PrimaryKey val date: String
){

}



package com.example.coffeeshop.ui.historyscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.OrderListBinding
import com.example.coffeeshop.domain.model.Order

class HistoryAdapter (private val list : List<Order>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(private val binding : OrderListBinding ) :
            RecyclerView.ViewHolder(binding.root){
                fun bindItem(order: Order){
                    binding.apply {
                        one.text = "1. Espresso($10) : Quantity :"
                        oneSize.text = "Size :"
                        two.text ="2. Cappuccino($20): Quantity:"
                        twoSize.text="Size :"
                        three.text = "3. Latte($15) : Quantity :"
                        threeSize.text="Size :"
                        four.text="4 : Frappe($40) : Quantity :"
                        fourSize.text ="Size :"
                        noteTitle.text = "Note For Cooking:"
                        dateTitle.text = "Date :"
                        totalTittle.text = "Total :"
                        etEsQ.text = order.quantityEspresso
                        etEsSi.text = order.sizeEspreeso
                        etCaQ.text = order.quantityCappucciano
                        etCaSi.text = order.sizeCappucciano
                        etLaQ.text = order.quantityLatte
                        etLaSi.text = order.sizeLatte
                        etFaQ.text = order.quantityFrappe
                        etFaSi.text = order.sizeFrappe
                        date.text  = order.date
                        note.text = order.note
                        amount.text = order.amount
                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(OrderListBinding.
        inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
       val task = list[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}

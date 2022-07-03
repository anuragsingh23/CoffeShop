package com.example.coffeeshop.ui.orderscreen

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.R
import com.example.coffeeshop.app.CoffeeApp
import com.example.coffeeshop.databinding.FragmentOrderBinding
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.utils.Time
import com.example.coffeeshop.utils.Toast
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding: FragmentOrderBinding get() = _binding!!

    private val viewModel: OrderViewModel by lazy {
        ViewModelProvider(this)[OrderViewModel::class.java]
    }
    private var radioEspresso: String = "  Size : small"
    private var radioCoppu: String = "  Size : small"
    private var radioLatte: String = "  Size : small"
    private var radioFrapee: String = "  Size : small"

    private var quantityEspresso = 0
    private var quantityCap = 0
    private var quantityLatte = 0
    private var quantityFrappe = 0

    private val espr = 10
    private val cap = 20
    private val latte = 15
    private val frappe = 40
    private var note : String? = null
    private var sum: Int = 0
    private val date = Time.getCurrentTime()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        binding.bill.text = "Items \n"
        (activity as AppCompatActivity).supportActionBar?.title = "Order Coffee"

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(com.example.coffeeshop.R.menu.order_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    com.example.coffeeshop.R.id.action_historyfragment -> {
                        navigateToHistoryFragment()
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel.espresso.observe(viewLifecycleOwner, Observer {
            binding.tvScoreEspresso.text = it.toString()
            quantityEspresso = it.toInt()
        })
        viewModel.cappa.observe(viewLifecycleOwner, Observer {
            binding.tvScoreCappa.text = it.toString()
            quantityCap = it.toInt()
        })

        viewModel.latte.observe(viewLifecycleOwner, Observer {
            binding.tvScoreLatte.text = it.toString()
            quantityLatte = it.toInt()
        })

        viewModel.frappe.observe(viewLifecycleOwner, Observer {
            binding.tvScoreFrappe.text = it.toString()
            quantityFrappe = it.toInt()
        })


        binding.btnIncrease1.setOnClickListener { viewModel.incrementEspresso() }
        binding.btnDecrease1.setOnClickListener { viewModel.decreaseEspresso() }


        binding.btnIncrease2.setOnClickListener { viewModel.incrementCappa() }
        binding.btnDecrease2.setOnClickListener { viewModel.decreaseCappa() }

        binding.btnIncrease3.setOnClickListener { viewModel.incrementLatte() }
        binding.btnDecrease3.setOnClickListener { viewModel.decreaseLatte() }

        binding.btnIncrease4.setOnClickListener { viewModel.incrementFrappe() }
        binding.btnDecrease4.setOnClickListener { viewModel.decreaseFrappe() }

        binding.bill.text = binding.bill.text.toString() + "Total Price : "

        binding.radio1.setOnCheckedChangeListener { _, checkedId ->
            radioEspresso = "Size : " + if (R.id.small_1 == checkedId) "Small"
            else if (R.id.medium_1 == checkedId) "Medium" else "Large"
        }

        binding.radio2.setOnCheckedChangeListener { _, checkedId ->
            radioCoppu = "Size : " + if (R.id.small_2 == checkedId) "Small"
            else if (R.id.medium_2 == checkedId) "Medium" else "Large"
        }
        binding.radio3.setOnCheckedChangeListener { _, checkedId ->
            radioLatte = "Size : " + if (R.id.small_3 == checkedId) "Small"
            else if (R.id.medium_3 == checkedId) "Medium" else "Large"
        }
        binding.radio4.setOnCheckedChangeListener { _, checkedId ->
            radioFrapee = "Size : " + if (R.id.small_4 == checkedId) "Small"
            else if (R.id.medium_4 == checkedId) "Medium" else "Large"
        }

        binding.btnPlaceOrder.setOnClickListener {
            it.isInvisible = true

            if ((quantityEspresso == 0) && (quantityCap == 0) && (quantityLatte == 0) && (quantityFrappe == 0)) {
                Toast.showToast("Please add atleat one item")
            } else {
                sum = espr * quantityEspresso + cap * quantityCap + latte * quantityLatte + frappe * quantityFrappe

                binding.bill.text =
                    "1. Espresso : Quantity : ${quantityEspresso}   ${radioEspresso} " + "\n" +
                            "2. Cappuccino : Quantity :  ${quantityCap}   ${radioCoppu} " + "\n" +
                            " 3. Latte : Quantity :  ${quantityLatte}   ${radioLatte} " + "\n" +
                            "4. Frappe : Quantity :  ${quantityFrappe}   ${radioFrapee}" + "\n" +
                            "\n" + "\n" + "Total Price : " + sum

                note = binding.etAddNote.toString()

                viewModel.viewModelScope.launch {
                    viewModel.addOrder(
                        quantityEspresso.toString(),
                        radioEspresso,
                        quantityCap.toString(),
                        radioCoppu,
                        quantityLatte.toString(),
                        radioLatte,
                        quantityFrappe.toString(),
                        radioFrapee,
                        note,
                        sum.toString(),
                        date
                    )
                }
            }

            Handler().postDelayed(
                {
                viewModel.viewModelScope.launch {

                    viewModel.reset()
                    binding.bill.text = "Items \n"
                    sum = 0
                    binding.radio1.check(R.id.small_1)
                    binding.radio2.check(R.id.small_2)
                    binding.radio3.check(R.id.small_3)
                    binding.radio4.check(R.id.small_4)
                    binding.etAddNote.text.clear()
                    it.isVisible = true

                }
            }, 5000)
            
        }
    }


    private fun navigateToHistoryFragment() {
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHistoryFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
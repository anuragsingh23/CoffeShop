package com.example.coffeeshop.ui.orderscreen

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.FragmentOrderBinding
import com.example.coffeeshop.ui.MainActivity
import com.example.coffeeshop.utils.Time
import com.example.coffeeshop.utils.Toast


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding : FragmentOrderBinding get()= _binding!!

    private val viewModel : OrderViewModel by lazy {
        ViewModelProvider(this)[OrderViewModel::class.java]
    }
    private var radioEspresso : String = ""
    private var radioCoppu : String = ""
    private var radioLatte : String = ""
    private var radioFrapee : String = ""




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        binding.bill.text = "Items \n"
        (activity as AppCompatActivity).supportActionBar?.title = "Order Coffee"


        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(com.example.coffeeshop.R.menu.order_menu,menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    com.example.coffeeshop.R.id.action_historyfragment-> {
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
            binding.bill.text =  binding.bill.text.toString() +
                    "1. Escpresso * " + binding.tvScoreEspresso.text.toString() + "\n"
        })
        viewModel.cappa.observe(viewLifecycleOwner, Observer {
            binding.tvScoreCappa.text = it.toString()
            binding.bill.text =  binding.bill.text.toString() +
                    "2. Cappucciano  * " + binding.tvScoreCappa.text.toString()
        })

        viewModel.latte.observe(viewLifecycleOwner, Observer {
            binding.tvScoreLatte.text = it.toString()
            binding.bill.text =  binding.bill.text.toString() +
                    "3. Latte * " + binding.tvScoreLatte.text.toString() + "\n"
        })

        viewModel.frappe.observe(viewLifecycleOwner, Observer {
            binding.tvScoreFrappe.text = it.toString()
            binding.bill.text = binding.bill.text.toString() +
                    "4. Frappe * " + binding.tvScoreFrappe.text.toString()
        })


        binding.btnIncrease1.setOnClickListener { viewModel.incrementEspresso() }
        binding.btnDecrease1.setOnClickListener { viewModel.decreaseEspresso() }


        binding.btnIncrease2.setOnClickListener { viewModel.incrementCappa()  }
        binding.btnDecrease2.setOnClickListener { viewModel.decreaseCappa() }

        binding.btnIncrease3.setOnClickListener { viewModel.incrementLatte() }
        binding.btnDecrease3.setOnClickListener { viewModel.decreaseLatte() }

        binding.btnIncrease4.setOnClickListener { viewModel.incrementFrappe() }
        binding.btnDecrease4.setOnClickListener { viewModel.decreaseFrappe() }




        binding.btnPlaceOrder.setOnClickListener {

            // delay on button
           // insert order in database
            // clear all value

        }
        binding.radio1.setOnCheckedChangeListener { _, checkedId ->
            radioEspresso = "Size : " + if (R.id.small_1 == checkedId) "Small"
            else if(R.id.medium_1==checkedId) "Medium" else "Large"
            Toast.showToast(radioEspresso)
        }

        binding.radio2.setOnCheckedChangeListener { _, checkedId ->
            radioCoppu = "Size : " + if (R.id.small_2 == checkedId) "Small"
            else if(R.id.medium_2==checkedId) "Medium" else "Large"
            Toast.showToast(radioCoppu)
        }
        binding.radio3.setOnCheckedChangeListener { _, checkedId ->
            radioLatte = "Size : " + if (R.id.small_3 == checkedId) "Small"
            else if(R.id.medium_3==checkedId) "Medium" else "Large"
            Toast.showToast(radioLatte)
        }
        binding.radio4.setOnCheckedChangeListener { _, checkedId ->
            radioFrapee = "Size : " + if (R.id.small_4 == checkedId) "Small"
            else if(R.id.medium_4==checkedId) "Medium" else "Large"
            Toast.showToast(radioFrapee)
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
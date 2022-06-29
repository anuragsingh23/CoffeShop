package com.example.coffeeshop.ui.orderscreen

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.databinding.FragmentOrderBinding
import com.example.coffeeshop.ui.MainActivity
import com.example.coffeeshop.ui.firstscreen.FirstScreenViewModel


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding : FragmentOrderBinding get()= _binding!!

    private val viewModel : OrderViewModel by lazy {
        ViewModelProvider(this)[OrderViewModel::class.java]
    }


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
        })

        viewModel.cappa.observe(viewLifecycleOwner, Observer {
            binding.tvScoreCappa.text = it.toString()
        })

        viewModel.latte.observe(viewLifecycleOwner, Observer {
            binding.tvScoreLatte.text = it.toString()
        })

        viewModel.frappe.observe(viewLifecycleOwner, Observer {
            binding.tvScoreFrappe.text = it.toString()
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
    }

    private fun navigateToHistoryFragment() {
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHistoryFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
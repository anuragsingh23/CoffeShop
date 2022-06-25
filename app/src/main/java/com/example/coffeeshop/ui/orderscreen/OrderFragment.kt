package com.example.coffeeshop.ui.orderscreen

import android.R
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding : FragmentOrderBinding get()= _binding!!

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
    }

    private fun navigateToHistoryFragment() {
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHistoryFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
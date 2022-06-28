package com.example.coffeeshop.ui.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel: FirstScreenViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding : FragmentFirstBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Coffee Shop"

        binding.btnEnter.setOnClickListener {
            navigateToOrderScreen()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun navigateToOrderScreen(){
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToOrderFragment())
    }
}
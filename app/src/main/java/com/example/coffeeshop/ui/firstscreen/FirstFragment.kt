package com.example.coffeeshop.ui.firstscreen

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.FragmentFirstBinding
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.utils.Toast
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel: FirstScreenViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding : FragmentFirstBinding get()= _binding!!

    lateinit var  name: String
    lateinit var phoneNumber: String



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

            name = binding.etName.text.toString().trim { it <= ' ' }
            phoneNumber = binding.etPhone.text.toString().trim { it <= ' ' }

            when {

                TextUtils.isEmpty(name) -> {
                    Toast.showToast("please enter name")
                }
                TextUtils.isEmpty(phoneNumber) -> {
                    Toast.showToast("please enter phone number")
                }
                else -> {
                    viewModel.viewModelScope.launch {
                        viewModel.addUserToDb(name,phoneNumber)
                    }
                    navigateToOrderScreen()
                }
            }
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
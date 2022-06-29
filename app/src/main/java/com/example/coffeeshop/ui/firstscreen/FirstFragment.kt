package com.example.coffeeshop.ui.firstscreen

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel: FirstScreenViewModel by viewModels()

    private lateinit var dataRef : DatabaseReference

    private var _binding: FragmentFirstBinding? = null
    private val binding : FragmentFirstBinding get()= _binding!!

    lateinit var  name: String
    lateinit var phoneNumber: String


    //inside Fragment
    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

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
                    addUserToDb(name,phoneNumber)
                    navigateToOrderScreen()
                }
            }
        }
    }
    private fun addUserToDb(name : String , phoneNumber : String) {
        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        dataRef.orderByChild("phoneNumber").equalTo(phoneNumber).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null)
                {
                    Toast.showToast("Already exist")
                    return
                }
                else{
                    //add user to database
                    val userID = dataRef.push().key!!
                    val user = User(name , phoneNumber, userID )
                    dataRef.child(userID).setValue(user).
                            addOnCompleteListener{
                                Toast.showToast("user insert complete")
                            }.addOnCanceledListener {
                                Toast.showToast("Firebase Exception")
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.showToast("Server Side Error 404 ")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun navigateToOrderScreen(){
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToOrderFragment())
    }
}
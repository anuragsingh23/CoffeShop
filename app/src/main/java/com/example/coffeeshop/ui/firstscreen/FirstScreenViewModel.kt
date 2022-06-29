package com.example.coffeeshop.ui.firstscreen

import androidx.lifecycle.ViewModel
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.utils.Toast
import com.google.firebase.database.*


class FirstScreenViewModel : ViewModel() {

    private lateinit var dataRef: DatabaseReference

    fun addUserToDb(name: String, phoneNumber: String) {
        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        dataRef.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                //Check user exist or not
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        Toast.showToast("Already exist")
                        return
                    } else {
                        //add user to database
                        val userID = dataRef.push().key!!
                        val user = User(name, phoneNumber, userID)
                        dataRef.child(userID).setValue(user).addOnCompleteListener {
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
}
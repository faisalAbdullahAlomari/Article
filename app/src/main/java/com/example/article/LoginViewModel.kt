package com.example.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val toast = MutableLiveData<String>()
    val loggedIn = MutableLiveData<Boolean>()

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                toast.value = "Login Successful"
                loggedIn.value = true
            }.addOnFailureListener {
                toast.value = "Email Or Password Is Not Correct"
            }
    }
}
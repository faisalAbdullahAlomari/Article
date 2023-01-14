package com.example.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val toast = MutableLiveData<String>()
    val accountCreated = MutableLiveData<Boolean>()

    fun registerUser(
        userName: String,
        email: String,
        password: String
    )
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                saveUserToFirebase(userName, email)
            }
            .addOnFailureListener {
                toast.value = it.localizedMessage
            }
    }

    private fun saveUserToFirebase(userName: String, email: String) {
        val map = mutableMapOf<String, String>()
        map["userName"] = userName
        map["email"] = email
        db.collection("users")
            .document(email)
            .set(map)
            .addOnSuccessListener {
                toast.value = "Account Created"
                accountCreated.value = true
            }.addOnFailureListener {
                toast.value = it.localizedMessage
            }
    }

    fun registerUser(UserName: String, email: String) {

    }
}
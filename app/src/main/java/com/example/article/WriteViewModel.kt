package com.example.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WriteViewModel: ViewModel() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    val articleAdd = MutableLiveData<Boolean>()

    fun addArticleToDatabase(data: Map<String, String>) {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(currentUser?.email ?: "")
            .collection("articles")
            .add(data)
            .addOnSuccessListener {
                articleAdd.value = true

            }
    }
}
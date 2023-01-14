package com.example.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class HomeViewModel: ViewModel() {

    val logOut = MutableLiveData<Boolean>()
    val articleListLiveData = MutableLiveData<List<Article>>()

    init {
        getArticle()
    }

    private fun getArticle(){
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
            .collection("articles")
            .addSnapshotListener { querySnapshot, _ ->
                val articleList = mutableListOf<Article>()
                querySnapshot?.documents?.map {
                    val article= it.toObject(Article::class.java)
                    if (article != null) {
                        articleList.add(article)
                    }
                    articleListLiveData.value = articleList
                }
            }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        logOut.value = true
    }
}
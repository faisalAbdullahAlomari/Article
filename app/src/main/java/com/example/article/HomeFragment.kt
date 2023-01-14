package com.example.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.article.databinding.FragmentArticleBinding
import com.example.article.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnLogout.setOnClickListener { parentFragmentManager.popBackStack() }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        adapter = Adapter(parentFragmentManager)

        activity as AppCompatActivity?

        setupArticles()

        binding.btnAddArticle.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, WriteFragment())
                .addToBackStack("WriteFragment")
                .commit()
        }
        viewModel.articleListLiveData.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        binding.btnLogout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SplashFragment())
                .addToBackStack("SplashFragment")
                .commit()
        }


    }


    private fun setupArticles(){
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.articleRecyclerView.adapter = adapter
    }
}

private fun NavigationView.setNavigationItemSelectedListener() {

}


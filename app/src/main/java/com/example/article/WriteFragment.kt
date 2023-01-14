package com.example.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.article.databinding.FragmentArticleBinding
import com.example.article.databinding.FragmentWriteBinding

class WriteFragment : Fragment() {

    private lateinit var binding: FragmentWriteBinding
    private lateinit var viewModel: WriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteBinding.inflate(layoutInflater)
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WriteViewModel::class.java)

        setupArticles()

        viewModel.articleAdd.observe(viewLifecycleOwner) { articleAdd ->
            if (articleAdd) {
                Toast.makeText(requireContext(), "Article Added", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun setupArticles() {

        binding.btnShare.setOnClickListener {
            val title = binding.etTitle1.text?.trim().toString()
            val article = binding.etWrite1.text?.trim().toString()

            when {
                title.isEmpty() -> {
                    binding.etTitle.error = getString(R.string.title)
                }

                article.isEmpty() -> {
                    binding.etWrite1.error = getString(R.string.write_your_article)
                }
                else -> {

                    val data = mutableMapOf<String, String>()
                    data["title"] = title
                    data["article"] = article

                    binding.btnShare.isEnabled = false
                    viewModel.addArticleToDatabase(data)
                }
            }
        }
    }
}


package com.example.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.article.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.btnLogin.setOnClickListener {
            if (checkInfo()) {
                viewModel.loginUser(
                    binding.etEmail1.text.toString().trim(),
                    binding.etPassword1.text.toString().trim()
                )
            }
        }
        binding.btnRegister.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack("RegisterFragment")
                .commit()
        }
        viewModel.loggedIn.observe(viewLifecycleOwner) { loggedIn ->
            if (loggedIn) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }
    }

    private fun checkInfo() : Boolean {
        return if (binding.etEmail1.text.toString().trim().isEmpty()) {
            binding.etEmail1.error = "Enter your email"
            false
        } else if (
            binding.etPassword1.text.toString().trim().isEmpty()) {
            binding.etEmail1.error = "Enter your password"
            false
        } else {
            true
        }
    }
}
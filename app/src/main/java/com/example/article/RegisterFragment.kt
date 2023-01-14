package com.example.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.article.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            if (checkInfo()) {
                viewModel.registerUser(
                    binding.etUserName1.text.toString(),
                    binding.etEmail1.text.toString(),
                    binding.etPassword1.text.toString()
                )
            }
        }
        binding.btnLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .addToBackStack("LoginFragment")
                .commit()
        }
        viewModel.accountCreated.observe(viewLifecycleOwner) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        viewModel.toast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInfo(): Boolean {
        if (binding.etUserName1.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "UserName Is Already Used", Toast.LENGTH_LONG).show()
            return false
        } else if (binding.etUserName1.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "UserName Cannot Be Empty", Toast.LENGTH_LONG).show()
            return false
        } else if (binding.etEmail1.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "Email Cannot Be Empty", Toast.LENGTH_LONG).show()
            return false
        } else if (binding.etPassword1.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "Password Cannot Be Empty", Toast.LENGTH_LONG).show()
            return false
        } else if (binding.etPassword1.text.toString() != binding.etCPassword1.text.toString()) {
            Toast.makeText(requireContext(), "Confirm Password Is Not Correct", Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }
    }
}
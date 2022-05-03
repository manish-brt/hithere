package com.happy.hithere.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.happy.hithere.AuthViewModel
import com.happy.hithere.databinding.FragmentLoginSignupBinding
import com.happy.hithere.databinding.FragmentSignupBinding

class SignupFragment: Fragment() {

    private var _binding : FragmentLoginSignupBinding? = null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
//        _binding?.usernameET?.isVisible = false;
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            submitBtn.setOnClickListener{
                authViewModel.signup(
                    usernameET.text.toString(),
                    emailET.text.toString(),
                    passwordET.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
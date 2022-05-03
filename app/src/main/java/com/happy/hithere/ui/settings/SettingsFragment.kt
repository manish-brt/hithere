package com.happy.hithere.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.happy.hithere.AuthViewModel
import com.happy.hithere.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({ lifecycle }) {
            _binding?.apply {
                bioET.setText(it?.bio ?: "")
                emailET.setText(it?.email ?: "")
                usernameET.setText(it?.username ?: "")
                imageET.setText(it?.image ?: "")
//                bioET.setText(it?.bio ?: "")

            }
        }

        _binding?.apply {
            updateBtn.setOnClickListener {
                authViewModel.update(
                    bio = bioET.text.toString(),
                    username = usernameET.text.toString().takeIf { it.isNotBlank() },
                    image = imageET.text.toString(),
                    email = emailET.text.toString().takeIf { it.isNotBlank() },
                    password = passwordET.text.toString().takeIf { it.isNotBlank() }
                )
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}
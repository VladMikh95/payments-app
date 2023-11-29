package ml.vladmikh.projects.payments_app.ui.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.hotel_app.R
import ml.vladmikh.projects.hotel_app.databinding.FragmentAuthorizationBinding
import ml.vladmikh.projects.payments_app.ui.model.AuthorizationRequest

@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private val viewModel by viewModels<AuthorizationViewModel>()
    private lateinit var binding: FragmentAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAuthorization.setOnClickListener {
            viewModel.getToken(
                AuthorizationRequest(
                    binding.editTextLogin.text.toString(),
                    binding.editTextPassword.text.toString())
            )
        }

        viewModel.authorizationResponse.observe(viewLifecycleOwner) {response ->
            if (response.success == "false") {
                binding.error.text= getString(R.string.error_wrong_login_or_password)
            } else {
                binding.error.text= ""
                val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToPaymentFragment(response.response.token)
                findNavController().navigate(action)
            }
        }

    }


}
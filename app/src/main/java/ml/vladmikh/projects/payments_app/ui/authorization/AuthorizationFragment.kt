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
import ml.vladmikh.projects.payments_app.util.ErrorPayment

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
            viewModel.getAuthorizationResponse(
                AuthorizationRequest(
                    binding.editTextLogin.text.toString(),
                    binding.editTextPassword.text.toString())
            )
        }

        viewModel.state.observe(viewLifecycleOwner) {state ->

            binding.progressBar.visibility = if (state == AuthorizationState.Loading) View.VISIBLE else View.GONE
            binding.textViewError.visibility = if (state is AuthorizationState.Error) View.VISIBLE else View.GONE

            if (state is AuthorizationState.Error) {

                binding.textViewError.text = when(state.error) {
                    ErrorPayment.CONNECTION_ERROR -> getString(R.string.text_error_connection_error)
                    ErrorPayment.ERROR_UNKNOWN -> getString(R.string.text_error_error_unknown)
                }
            }

            if (state is AuthorizationState.Loaded) {

                val response = state.authorizationResponse

                if (response.success == "false") {
                    binding.textViewErrorLogin.text= getString(R.string.error_wrong_login_or_password)
                } else {
                    binding.textViewErrorLogin.text= ""

                    //Присваиваем state значение AuthorizationState.Initial,
                    // если этого не сделать, то при нажатии кнопки назад в PaymentFragment
                    // сначала откроется AuthorizationFragment и так как состояние этого фрагмента будет
                    //Loaded то автоматически откроется PaymentFragment
                    viewModel.initialState()
                    val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToPaymentFragment(response.response.token)
                    findNavController().navigate(action)
                }
            }
        }
    }

}
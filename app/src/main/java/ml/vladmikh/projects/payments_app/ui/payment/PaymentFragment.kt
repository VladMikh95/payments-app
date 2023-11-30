package ml.vladmikh.projects.payments_app.ui.payment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.hotel_app.R
import ml.vladmikh.projects.hotel_app.databinding.FragmentPaymentBinding
import ml.vladmikh.projects.payments_app.ui.adapter.PaymentAdapter
import ml.vladmikh.projects.payments_app.ui.authorization.AuthorizationFragmentDirections
import ml.vladmikh.projects.payments_app.ui.authorization.AuthorizationState
import ml.vladmikh.projects.payments_app.util.ErrorPayment


@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private val viewModel by viewModels<PaymentViewModel>()
    private lateinit var binding: FragmentPaymentBinding
    private val args: PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentPaymentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PaymentAdapter()

        viewModel.getPayment(args.token)

        viewModel.state.observe(viewLifecycleOwner) {state ->

            binding.progressBar.visibility = if (state == PaymentState.Loading) View.VISIBLE else View.GONE
            binding.textViewError.visibility = if (state is PaymentState.Error) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (state is PaymentState.Loaded) View.VISIBLE else View.GONE


            if (state is PaymentState.Error) {

                binding.textViewError.text = when(state.error) {
                    ErrorPayment.CONNECTION_ERROR -> getString(R.string.text_error_connection_error)
                    ErrorPayment.ERROR_UNKNOWN -> getString(R.string.text_error_error_unknown)
                }
            }

            if (state is PaymentState.Loaded) {

                val listPayment = state.listPayments

                binding.recyclerView.adapter = adapter

                adapter.submitList(listPayment)
            }
        }

        binding.signOutBotton.setOnClickListener {
            findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToStartFragment())

        }
    }

}
package ml.vladmikh.projects.payments_app.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.hotel_app.R
import ml.vladmikh.projects.hotel_app.databinding.FragmentPaymentBinding


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

        viewModel.getPayment(args.token)

        viewModel.listPayment.observe(viewLifecycleOwner) {listPayment ->
            binding.textView.text = listPayment.toString()
        }
    }

}
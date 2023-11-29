package ml.vladmikh.projects.payments_app.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.hotel_app.R
import ml.vladmikh.projects.hotel_app.databinding.FragmentPaymentBinding


@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private val viewModel by viewModels<PaymentViewModel>()
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

}
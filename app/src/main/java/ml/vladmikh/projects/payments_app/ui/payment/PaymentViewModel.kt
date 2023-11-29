package ml.vladmikh.projects.payments_app.ui.payment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ml.vladmikh.projects.payments_app.data.repository.AuthorizationResponseRepository
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: AuthorizationResponseRepository): ViewModel() {
}
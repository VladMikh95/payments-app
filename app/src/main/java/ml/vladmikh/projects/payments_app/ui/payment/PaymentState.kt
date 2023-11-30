package ml.vladmikh.projects.payments_app.ui.payment

import ml.vladmikh.projects.payments_app.data.network.model.Payment
import ml.vladmikh.projects.payments_app.util.ErrorPayment

sealed interface PaymentState {

    object Initial: PaymentState
    object Loading : PaymentState
    data class Loaded(val listPayments: ArrayList<Payment>) : PaymentState
    data class Error(val error: ErrorPayment) : PaymentState
}
package ml.vladmikh.projects.payments_app.data.network.model

data class PaymentResponse(
    val response: List<Payment>,
    val success: String
)
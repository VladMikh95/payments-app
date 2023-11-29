package ml.vladmikh.projects.payments_app.data.repository

import ml.vladmikh.projects.payments_app.data.network.ApiService
import ml.vladmikh.projects.payments_app.data.network.model.PaymentResponse
import javax.inject.Inject

class PaymentResponseRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPayments(token: String): PaymentResponse {
        return apiService.getPayments(token)
    }
}
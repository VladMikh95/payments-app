package ml.vladmikh.projects.payments_app.data.network

import ml.vladmikh.projects.payments_app.data.network.model.AuthorizationResponse
import ml.vladmikh.projects.payments_app.data.network.model.PaymentResponse
import ml.vladmikh.projects.payments_app.ui.model.AuthorizationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    //Заголовок app-key и v сделал статичными, потому что это (по-моему) не зависит
    // от пользователя,но можно сделать и динамичными
    @Headers("Content-type: application/json","app-key: 12345", "v: 1")
    @POST("login")
    suspend fun getAuthorizationResponse( @Body authorizationRequest: AuthorizationRequest): AuthorizationResponse

    @Headers("Content-type: application/json", "app-key: 12345", "v: 1")
    @GET("payments")
    suspend fun getPayments(@Header("token") token: String): PaymentResponse
}
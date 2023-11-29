package ml.vladmikh.projects.payments_app.data.repository

import ml.vladmikh.projects.payments_app.data.network.ApiService
import ml.vladmikh.projects.payments_app.data.network.model.AuthorizationResponse
import ml.vladmikh.projects.payments_app.ui.model.AuthorizationRequest
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthorizationResponseRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getToken(authorizationRequest: AuthorizationRequest): AuthorizationResponse {

       return apiService.getAuthorizationResponse(authorizationRequest)
    }

    suspend fun getPayments(): Response<ResponseBody> {
        val string = "7b7c0a690bee2e8d90512ed1b57e19f0"
        return apiService.getPayments( string)
    }
}
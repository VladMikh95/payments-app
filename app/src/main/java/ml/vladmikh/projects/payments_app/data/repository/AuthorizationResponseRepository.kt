package ml.vladmikh.projects.payments_app.data.repository

import ml.vladmikh.projects.payments_app.data.network.ApiService
import ml.vladmikh.projects.payments_app.data.network.model.AuthorizationResponse
import ml.vladmikh.projects.payments_app.ui.model.AuthorizationRequest
import javax.inject.Inject

class AuthorizationResponseRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getAuthorizationResponse(authorizationRequest: AuthorizationRequest): AuthorizationResponse {

       return apiService.getAuthorizationResponse(authorizationRequest)
    }


}
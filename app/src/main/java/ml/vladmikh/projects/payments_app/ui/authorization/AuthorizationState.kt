package ml.vladmikh.projects.payments_app.ui.authorization

import ml.vladmikh.projects.payments_app.data.network.model.AuthorizationResponse
import ml.vladmikh.projects.payments_app.util.ErrorPayment


sealed interface AuthorizationState {

    object Initial: AuthorizationState
    object Loading : AuthorizationState
    data class Loaded(val authorizationResponse: AuthorizationResponse) : AuthorizationState
    data class Error(val error: ErrorPayment) : AuthorizationState
}
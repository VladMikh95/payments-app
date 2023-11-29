package ml.vladmikh.projects.payments_app.data.network.model

data class AuthorizationResponse(
    val response: Response,
    val success: String
)
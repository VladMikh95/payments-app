package ml.vladmikh.projects.payments_app.ui.model

//Класс объекты которого передаются в POST запрос, при регистрации пользователя
data class AuthorizationRequest (
    val login : String,
    val password: String
)

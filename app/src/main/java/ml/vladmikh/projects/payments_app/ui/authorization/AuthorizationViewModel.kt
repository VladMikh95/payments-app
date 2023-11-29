package ml.vladmikh.projects.payments_app.ui.authorization

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.payments_app.data.network.model.AuthorizationResponse
import ml.vladmikh.projects.payments_app.data.repository.AuthorizationResponseRepository
import ml.vladmikh.projects.payments_app.ui.model.AuthorizationRequest
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(private val repository: AuthorizationResponseRepository): ViewModel() {

    // Переменная, которая хранит ответ при авторизации пользователя
    private var _authorizationResponse = MutableLiveData<AuthorizationResponse>()
    val authorizationResponse: LiveData<AuthorizationResponse> get() = _authorizationResponse
    fun getToken(authorizationRequest: AuthorizationRequest) {
        viewModelScope.launch {

            try {
                _authorizationResponse.value = repository.getToken(authorizationRequest)
            } catch (e: Exception) {
                Log.i("abcError", e.message.toString())
            }
        }

    }
}
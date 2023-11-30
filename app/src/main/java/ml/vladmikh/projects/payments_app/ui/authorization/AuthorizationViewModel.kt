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
import ml.vladmikh.projects.payments_app.ui.payment.PaymentState
import ml.vladmikh.projects.payments_app.util.ErrorPayment
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(private val repository: AuthorizationResponseRepository): ViewModel() {

    //Переменая которая отвечает за состояние экрана
    private var _state = MutableLiveData<AuthorizationState>().apply {
        value = AuthorizationState.Initial
    }
    val state: LiveData<AuthorizationState> get() = _state

    fun getAuthorizationResponse(authorizationRequest: AuthorizationRequest) {
        viewModelScope.launch {

            _state.value = AuthorizationState.Loading

            try {
                val authorizationResponse = repository.getAuthorizationResponse(authorizationRequest)
                _state.value = AuthorizationState.Loaded(authorizationResponse)
            } catch (e: IOException) {
                _state.value = AuthorizationState.Error(ErrorPayment.CONNECTION_ERROR)
            } catch (e: HttpException) {
                _state.value = AuthorizationState.Error(ErrorPayment.ERROR_UNKNOWN)

            }
        }
    }

    fun initialState() {
        _state.value = AuthorizationState.Initial
    }
}
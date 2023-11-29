package ml.vladmikh.projects.payments_app.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.payments_app.data.network.model.Payment
import ml.vladmikh.projects.payments_app.data.network.model.PaymentResponse
import ml.vladmikh.projects.payments_app.data.repository.PaymentResponseRepository
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: PaymentResponseRepository): ViewModel() {

    private var _paymentResponse = MutableLiveData<PaymentResponse>()

    private var _listPayments = MutableLiveData<ArrayList<Payment>>()
    val listPayment: LiveData<ArrayList<Payment>> get() = _listPayments
    fun getPayment(token: String) {
        viewModelScope.launch {

            _paymentResponse.value = repository.getPayments(token)
            checkPayments()
        }

    }
    //Метод для проверки корректных данных платежей
    fun checkPayments() {

        val newListPayments = ArrayList<Payment>()

        val payments  = _paymentResponse.value?.response
        //Проходим по платежам и добавляем в новый список платежей только корректные данные
        if (payments != null) {
            for(payment in payments) {
                if(isInt(payment.id) &&
                    isCorrectString(payment.title) &&
                    isInt(payment.created) &&
                    isNumeric(payment.amount)) {

                    newListPayments.add(
                        Payment(payment.amount,
                            convertUnixToDate(payment.created),
                            payment.id,
                            payment.title)
                    )

                }
            }
        }

        _listPayments.value = newListPayments
    }

    //Проверка является ли строка положительным числом
    private fun isNumeric(string: String): Boolean {
        return try {
            if(string.toDouble() > 0){
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    //Проверка является ли строка целочисленным числом
    private fun isInt(string: String): Boolean {
        return try {
            string.toInt()
            true
        } catch (e: Exception) {
            false
        }
    }

    //Проверка содердит ли строка корректные данные
    private fun isCorrectString(string: String): Boolean {
        return string.isNotEmpty()
    }

    //Метод преобразует строку Unix Timestamp в дату
    private fun convertUnixToDate(string: String): String {
        val date = Date(string.toLong())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(date)

    }

}
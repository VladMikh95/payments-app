package ml.vladmikh.projects.payments_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ml.vladmikh.projects.payments_app.data.network.ApiService
import ml.vladmikh.projects.payments_app.data.repository.AuthorizationResponseRepository
import ml.vladmikh.projects.payments_app.data.repository.PaymentResponseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://easypay.world/api-test/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthorizationResponseRepository( mainService: ApiService): AuthorizationResponseRepository =AuthorizationResponseRepository(mainService)

    @Provides
    @Singleton
    fun providePaymentResponseRepository( mainService: ApiService): PaymentResponseRepository =PaymentResponseRepository(mainService)

}
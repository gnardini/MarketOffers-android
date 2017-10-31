package com.gnardini.marketoffers.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServices(private val hostManager: HostManager) {

    private val services = HashMap<Class<*>, Any>()
    private var retrofit = createRetrofit()

    fun <ServiceType> getService(clazz: Class<ServiceType>): ServiceType {
        if (!services.containsKey(clazz)) {
            val service: ServiceType = retrofit.create(clazz)
            services.put(clazz, service as Any)
        }
        return services[clazz] as ServiceType
    }

    fun refresh() {
        createRetrofit()
    }

    private fun createRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(hostManager.getHost())
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

    private fun createClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }
}

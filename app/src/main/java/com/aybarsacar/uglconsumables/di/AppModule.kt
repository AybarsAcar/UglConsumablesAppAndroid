package com.aybarsacar.uglconsumables.di

import com.aybarsacar.uglconsumables.BuildConfig
import com.aybarsacar.uglconsumables.data.remote.UglConsumablesAppApi
import com.aybarsacar.uglconsumables.data.repository.AreaOfWorkRepositoryImpl
import com.aybarsacar.uglconsumables.data.repository.ConsumableRepositoryImpl
import com.aybarsacar.uglconsumables.data.repository.OrderRepositoryImpl
import com.aybarsacar.uglconsumables.data.repository.UserRepositoryImpl
import com.aybarsacar.uglconsumables.domain.repository.AreaOfWorkRepository
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.domain.repository.OrderRepository
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import com.aybarsacar.uglconsumables.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Dependency injection container for the application
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


  @Provides
  @Singleton
  fun provideUsersApi(): UglConsumablesAppApi {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .client(
        OkHttpClient.Builder().also { client ->
          if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
          }
        }.build()
      )
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(UglConsumablesAppApi::class.java)
  }


  @Provides
  @Singleton
  fun provideUserRepository(api: UglConsumablesAppApi): UserRepository {
    return UserRepositoryImpl(api)
  }


  @Provides
  @Singleton
  fun provideConsumableRepository(api: UglConsumablesAppApi): ConsumableRepository {
    return ConsumableRepositoryImpl(api)
  }


  @Provides
  @Singleton
  fun provideAreaOfWorkRepository(api: UglConsumablesAppApi): AreaOfWorkRepository {
    return AreaOfWorkRepositoryImpl(api)
  }


  @Provides
  @Singleton
  fun provideOrderRepository(api: UglConsumablesAppApi): OrderRepository {
    return OrderRepositoryImpl(api)
  }
}
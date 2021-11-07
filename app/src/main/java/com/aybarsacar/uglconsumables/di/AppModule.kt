package com.aybarsacar.uglconsumables.di

import com.aybarsacar.uglconsumables.data.remote.UserApi
import com.aybarsacar.uglconsumables.data.repository.UserRepositoryImpl
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import com.aybarsacar.uglconsumables.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
  fun provideUsersApi(): UserApi {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(UserApi::class.java)
  }


  @Provides
  @Singleton
  fun provideUserRepository(api: UserApi): UserRepository {
    return UserRepositoryImpl()
  }

}
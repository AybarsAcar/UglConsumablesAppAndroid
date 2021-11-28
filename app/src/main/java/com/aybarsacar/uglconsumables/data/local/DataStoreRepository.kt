package com.aybarsacar.uglconsumables.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


private val Context.uglDataStore by preferencesDataStore(Constants.PREFERENCES_NAME)

/**
 * used to store the user details
 */
class DataStoreRepository @Inject constructor(@ApplicationContext private val _context: Context) {

  // TODO: decide if you want to store all the values
  private object PreferenceKeys {
    val username = stringPreferencesKey(Constants.PREFERENCES_USERNAME)
    val token = stringPreferencesKey(Constants.PREFERENCES_TOKEN)
    val email = stringPreferencesKey(Constants.PREFERENCES_EMAIL)
    val department = stringPreferencesKey(Constants.PREFERENCES_DEPARTMENT)
  }

  private val _dataStore: DataStore<Preferences> = _context.uglDataStore


  suspend fun saveUserDetails(username: String, token: String, email: String, department: String) {

    _dataStore.edit { preferences ->
      preferences[PreferenceKeys.username] = username
      preferences[PreferenceKeys.token] = token
      preferences[PreferenceKeys.email] = email
      preferences[PreferenceKeys.department] = department
    }
  }


  val readUserDetails: Flow<AccountDto> = _dataStore.data.catch { e ->

    if (e is IOException) {
      emit(emptyPreferences())
    } else {
      throw e
    }
  }
    .map { preferences ->
      val username = preferences[PreferenceKeys.username] ?: ""
      val token = preferences[PreferenceKeys.token] ?: ""
      val email = preferences[PreferenceKeys.email] ?: ""
      val department = preferences[PreferenceKeys.department] ?: ""

      AccountDto(username, email, token, department)
    }


  suspend fun clear() {
    _dataStore.edit {
      it.clear()
    }
  }

}
package barrera.alejandro.busdown.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_storage")

@Singleton
class PreferenceStorageImpl @Inject constructor(
    @ApplicationContext context: Context
): PreferenceStorage {
    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val SHOW_BASIC_INFO_FRAGMENT = booleanPreferencesKey("show_basic_info_fragment")
    }

    override fun savedKey() = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }.map { preferences ->
        preferences[PreferencesKeys.SHOW_BASIC_INFO_FRAGMENT] ?: true
    }

    override suspend fun setSavedKey(value: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.SHOW_BASIC_INFO_FRAGMENT] = value
        }
    }
}
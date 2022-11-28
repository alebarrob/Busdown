package barrera.alejandro.busdown.model.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    fun savedKey() : Flow<Boolean>
    suspend fun setSavedKey(value: Boolean)
}






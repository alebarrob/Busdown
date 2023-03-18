package barrera.alejandro.busdown.core.domain.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    fun savedKey() : Flow<Boolean>
    suspend fun setSavedKey(value: Boolean)
}






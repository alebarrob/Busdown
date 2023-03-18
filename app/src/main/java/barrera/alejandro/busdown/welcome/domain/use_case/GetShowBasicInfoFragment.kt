package barrera.alejandro.busdown.welcome.domain.use_case

import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
import kotlinx.coroutines.flow.Flow

class GetShowBasicInfoFragment(private val preferenceStorageImpl: PreferenceStorage) {
    operator fun invoke(): Flow<Boolean> {
        return preferenceStorageImpl.savedKey()
    }
}
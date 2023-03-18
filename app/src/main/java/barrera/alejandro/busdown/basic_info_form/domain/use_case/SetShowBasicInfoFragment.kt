package barrera.alejandro.busdown.basic_info_form.domain.use_case

import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage

class SetShowBasicInfoFragment(private val preferenceStorageImpl: PreferenceStorage) {
    suspend operator fun invoke(showBasicInfoFragment: Boolean) {
        preferenceStorageImpl.setSavedKey(showBasicInfoFragment)
    }
}
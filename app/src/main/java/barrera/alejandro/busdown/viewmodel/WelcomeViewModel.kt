package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import barrera.alejandro.busdown.model.preference.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    preferenceStorageImpl: PreferenceStorage
) : ViewModel() {
    val showBasicInfoFragment = preferenceStorageImpl.savedKey().asLiveData()
}
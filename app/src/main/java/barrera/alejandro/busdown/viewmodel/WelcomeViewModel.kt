package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.model.preference.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val preferenceStorageImpl: PreferenceStorage
) : ViewModel() {
    val showBasicInfoFragment = preferenceStorageImpl.savedKey().asLiveData()

    fun setShowBasicInfoFragment(key: Boolean) {
        viewModelScope.launch {
            preferenceStorageImpl.setSavedKey(key)
        }
    }
}
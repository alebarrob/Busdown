package barrera.alejandro.busdown.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.core.domain.use_cases.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    val emails = MutableLiveData<List<String>>()

    fun loadEmails() {
        viewModelScope.launch {
            coreUseCases.getContactEmails.invoke().collect { loadedEmails ->
                emails.value = loadedEmails
            }
        }
    }
}
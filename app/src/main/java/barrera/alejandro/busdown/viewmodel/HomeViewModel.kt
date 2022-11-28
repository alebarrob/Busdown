package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import barrera.alejandro.busdown.model.repository.ContactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    contactRepositoryImpl: ContactRepositoryImpl
) : ViewModel() {
    val emails = contactRepositoryImpl.emails
}
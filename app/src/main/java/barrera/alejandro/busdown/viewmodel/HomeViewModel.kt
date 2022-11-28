package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import barrera.alejandro.busdown.model.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    contactRepositoryImpl: ContactRepository
) : ViewModel() {
    val emails = contactRepositoryImpl.emails
}
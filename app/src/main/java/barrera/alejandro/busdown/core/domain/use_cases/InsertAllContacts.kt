package barrera.alejandro.busdown.core.domain.use_cases

import barrera.alejandro.busdown.core.domain.model.Contact
import barrera.alejandro.busdown.core.domain.repository.ContactRepository

class InsertAllContacts(private val repository: ContactRepository) {
    suspend operator fun invoke(contacts: List<Contact>) {
        return repository.insertAllContacts(contacts)
    }
}
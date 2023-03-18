package barrera.alejandro.busdown.settings.domain.use_case

import barrera.alejandro.busdown.core.domain.repository.ContactRepository

class DeleteAllContactsExceptBusUp(private val repository: ContactRepository) {
    suspend operator fun invoke() {
        return repository.deleteAllContactsExceptBusUp()
    }
}
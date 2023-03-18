package barrera.alejandro.busdown.core.domain.use_cases

import barrera.alejandro.busdown.core.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class GetContactEmails(private val repository: ContactRepository) {
    operator fun invoke(): Flow<List<String>> {
        return repository.getContactEmails()
    }
}
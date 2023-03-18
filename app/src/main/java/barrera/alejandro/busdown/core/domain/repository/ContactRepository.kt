package barrera.alejandro.busdown.core.domain.repository

import barrera.alejandro.busdown.core.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContactEmails(): Flow<List<String>>

    suspend fun insertAllContacts(contacts: List<Contact>)

    suspend fun deleteAllContactsExceptBusUp()
}
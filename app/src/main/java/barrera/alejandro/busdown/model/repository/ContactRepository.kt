package barrera.alejandro.busdown.model.repository

import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    val emails: Flow<List<String>>

    suspend fun insertAllContacts(contacts: List<Contact>)
    suspend fun insertContact(contact: Contact)
    suspend fun deleteAllContacts()
}
package barrera.alejandro.busdown.model.repository

import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    val contacts: Flow<List<Contact>>

    fun insertContact(contact: Contact)
}
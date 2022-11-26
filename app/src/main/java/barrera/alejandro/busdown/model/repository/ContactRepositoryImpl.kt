package barrera.alejandro.busdown.model.repository

import barrera.alejandro.busdown.model.dao.ContactDao
import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) : ContactRepository {
    override val contacts: Flow<List<Contact>> get() = contactDao.getContacts()

    override fun insertContact(contact: Contact) = contactDao.insertContact(contact)
}
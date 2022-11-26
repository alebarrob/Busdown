package barrera.alejandro.busdown.model.repository

import barrera.alejandro.busdown.model.dao.ContactDao
import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) : ContactRepository {
    override val emails: Flow<List<String>> get() = contactDao.getEmails()

    override suspend fun insertAllContacts(contacts: List<Contact>) = contactDao.insertAllContacts(contacts)
    override suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)
    override suspend fun deleteAllContacts() = contactDao.deleteAllContacts()
}
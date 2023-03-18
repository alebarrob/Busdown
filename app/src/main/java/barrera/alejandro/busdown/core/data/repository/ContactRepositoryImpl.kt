package barrera.alejandro.busdown.core.data.repository

import barrera.alejandro.busdown.core.data.dao.ContactDao
import barrera.alejandro.busdown.core.domain.model.Contact
import barrera.alejandro.busdown.core.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) : ContactRepository {
    override fun getContactEmails(): Flow<List<String>> {
        return contactDao.getEmails()
    }

    override suspend fun insertAllContacts(contacts: List<Contact>) {
        contactDao.insertAllContacts(contacts)
    }

    override suspend fun deleteAllContactsExceptBusUp() {
        contactDao.deleteAllContactsExceptBusUp()
    }
}
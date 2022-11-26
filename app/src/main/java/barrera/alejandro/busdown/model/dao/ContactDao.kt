package barrera.alejandro.busdown.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT DISTINCT email FROM contacts")
    fun getEmails(): Flow<List<String>>

    @Insert(onConflict = IGNORE)
    suspend fun insertContact(contact: Contact)

    @Insert(onConflict = IGNORE)
    suspend fun insertAllContacts(contact: List<Contact>)

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()
}
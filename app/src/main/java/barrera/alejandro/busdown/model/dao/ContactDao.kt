package barrera.alejandro.busdown.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import barrera.alejandro.busdown.model.entity.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getContacts(): Flow<List<Contact>>

    @Insert
    fun insertContact(contact: Contact)
}
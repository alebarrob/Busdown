package barrera.alejandro.busdown.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.busdown.core.data.dao.ContactDao
import barrera.alejandro.busdown.core.domain.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class BusdownRoomDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao
}
package barrera.alejandro.busdown.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.busdown.model.dao.ContactDao
import barrera.alejandro.busdown.model.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class BusdownRoomDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao
}
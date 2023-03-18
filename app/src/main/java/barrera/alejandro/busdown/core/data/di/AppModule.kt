package barrera.alejandro.busdown.core.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.busdown.core.data.dao.ContactDao
import barrera.alejandro.busdown.core.data.database.BusdownRoomDatabase
import barrera.alejandro.busdown.core.data.repository.ContactRepositoryImpl
import barrera.alejandro.busdown.core.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): BusdownRoomDatabase {
        return Room.databaseBuilder(
            context,
            BusdownRoomDatabase::class.java,
            "busdown_database"
        ).createFromAsset("database/busdown_database.db").build()
    }

    @Singleton
    @Provides
    fun provideContactDao(dataBase: BusdownRoomDatabase): ContactDao {
        return dataBase.contactDao()
    }

    @Singleton
    @Provides
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepositoryImpl(contactDao)
    }
}
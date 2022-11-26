package barrera.alejandro.busdown.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.busdown.model.database.BusdownRoomDatabase
import barrera.alejandro.busdown.model.dao.ContactDao
import barrera.alejandro.busdown.model.repository.ContactRepository
import barrera.alejandro.busdown.model.repository.ContactRepositoryImpl
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
    ) = Room.databaseBuilder(
        context,
        BusdownRoomDatabase::class.java,
        "busdown_database"
    ).fallbackToDestructiveMigration()
        .createFromAsset("database/busdown_database.db")
        .build()

    @Singleton
    @Provides
    fun provideContactDao(dataBase: BusdownRoomDatabase) = dataBase.contactDao()

    @Singleton
    @Provides
    fun contactRepositoryImpl(contactDao: ContactDao): ContactRepository = ContactRepositoryImpl(contactDao)
}
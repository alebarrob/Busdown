package barrera.alejandro.busdown.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.busdown.model.database.BusdownRoomDatabase
import barrera.alejandro.busdown.model.dao.ContactDao
import barrera.alejandro.busdown.model.repository.ContactRepository
import barrera.alejandro.busdown.model.repository.ContactRepositoryImpl
import barrera.alejandro.busdown.viewmodel.EmailFormatter
import barrera.alejandro.busdown.viewmodel.EmailValidator
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
    ).createFromAsset("database/busdown_database.db").build()

    @Singleton
    @Provides
    fun provideContactDao(dataBase: BusdownRoomDatabase) = dataBase.contactDao()

    @Singleton
    @Provides
    fun contactRepositoryImpl(contactDao: ContactDao): ContactRepository = ContactRepositoryImpl(contactDao)

    @Provides
    fun emailFormatter() = EmailFormatter()

    @Provides
    fun emailValidator() = EmailValidator()
}
package barrera.alejandro.busdown.di

import barrera.alejandro.busdown.model.preference.PreferenceStorage
import barrera.alejandro.busdown.model.preference.PreferenceStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {
    @Binds
    abstract fun bindsPreferenceStorage(preferenceStorageImpl: PreferenceStorageImpl): PreferenceStorage
}
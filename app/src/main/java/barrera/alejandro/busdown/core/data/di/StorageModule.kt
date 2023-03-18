package barrera.alejandro.busdown.core.data.di

import barrera.alejandro.busdown.core.data.preferences.PreferenceStorageImpl
import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
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
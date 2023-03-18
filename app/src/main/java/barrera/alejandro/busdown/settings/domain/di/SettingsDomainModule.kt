package barrera.alejandro.busdown.settings.domain.di

import barrera.alejandro.busdown.core.domain.repository.ContactRepository
import barrera.alejandro.busdown.core.domain.use_cases.*
import barrera.alejandro.busdown.settings.domain.use_case.DeleteAllContactsExceptBusUp
import barrera.alejandro.busdown.settings.domain.use_case.SettingsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SettingsDomainModule {
    @ViewModelScoped
    @Provides
    fun provideSettingsUseCases(contactRepository: ContactRepository): SettingsUseCases {
        return SettingsUseCases(
            deleteAllContactsExceptBusUp = DeleteAllContactsExceptBusUp(contactRepository)
        )
    }
}
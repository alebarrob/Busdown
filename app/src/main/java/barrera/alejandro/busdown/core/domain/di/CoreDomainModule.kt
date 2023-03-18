package barrera.alejandro.busdown.core.domain.di

import barrera.alejandro.busdown.core.domain.repository.ContactRepository
import barrera.alejandro.busdown.core.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CoreDomainModule {
    @ViewModelScoped
    @Provides
    fun provideCoreUseCases(contactRepository: ContactRepository): CoreUseCases {
        return CoreUseCases(
            getContactEmails = GetContactEmails(contactRepository),
            insertAllContacts = InsertAllContacts(contactRepository),
            formatEmails = FormatEmails(),
            validateEmails = ValidateEmails()
        )
    }
}
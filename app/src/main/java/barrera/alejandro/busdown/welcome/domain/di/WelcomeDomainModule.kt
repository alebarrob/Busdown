package barrera.alejandro.busdown.welcome.domain.di

import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
import barrera.alejandro.busdown.welcome.domain.use_case.GetShowBasicInfoFragment
import barrera.alejandro.busdown.welcome.domain.use_case.WelcomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WelcomeDomainModule {
    @ViewModelScoped
    @Provides
    fun provideWelcomeUseCases(preferenceStorage: PreferenceStorage): WelcomeUseCases {
        return WelcomeUseCases(
            getShowBasicInfoFragment = GetShowBasicInfoFragment(preferenceStorage)
        )
    }
}
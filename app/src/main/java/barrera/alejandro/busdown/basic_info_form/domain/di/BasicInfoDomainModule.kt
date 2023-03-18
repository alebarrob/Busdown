package barrera.alejandro.busdown.basic_info_form.domain.di

import barrera.alejandro.busdown.basic_info_form.domain.use_case.BasicInfoUseCases
import barrera.alejandro.busdown.basic_info_form.domain.use_case.SetShowBasicInfoFragment
import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object BasicInfoDomainModule {
    @ViewModelScoped
    @Provides
    fun provideBasicInfoUseCases(preferenceStorage: PreferenceStorage): BasicInfoUseCases {
        return BasicInfoUseCases(
            setShowBasicInfoFragment = SetShowBasicInfoFragment(preferenceStorage)
        )
    }
}
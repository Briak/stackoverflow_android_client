package com.briak.stackoverflowclient.model.di.application

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
abstract class NavigationModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()
    }
}
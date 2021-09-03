package com.vali.weather.di

import android.content.Context
import androidx.room.Room
import com.vali.weather.model.database.Database
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
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database {
        return Room.databaseBuilder(applicationContext, Database::class.java, "db")
            .fallbackToDestructiveMigration().build()
    }
}
package ru.nurdaulet.denettest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.nurdaulet.denettest.data.database.NodeDAO
import ru.nurdaulet.denettest.data.database.NodeDatabase
import ru.nurdaulet.denettest.data.repository.NodeRepository
import ru.nurdaulet.denettest.data.repository.NodeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNodeDatabase(@ApplicationContext context: Context): NodeDatabase {
        return NodeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNodeDAO(database: NodeDatabase): NodeDAO {
        return database.nodeDao
    }

    @Provides
    @Singleton
    fun provideNodeRepository(dao: NodeDAO): NodeRepository {
        return NodeRepositoryImpl(dao)
    }
}
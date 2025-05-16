package com.yeabsira.todolist.di

import android.content.Context
import com.yeabsira.todolist.data.TodoDatabase
import com.yeabsira.todolist.data.TodoDao
import com.yeabsira.todolist.network.TodoApiService
import com.yeabsira.todolist.network.RetrofitInstance
import com.yeabsira.todolist.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoApiService(): TodoApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideTodoRepository(api: TodoApiService, dao: TodoDao): TodoRepository {
        return TodoRepository(api, dao)
    }
}
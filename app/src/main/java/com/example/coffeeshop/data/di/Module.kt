package com.example.coffeeshop.data.di

import android.app.Application
import androidx.room.Room
import com.example.coffeeshop.data.local.OrderDatabase
import com.example.coffeeshop.data.repository.OrderRepositoryImpl
import com.example.coffeeshop.domain.repo.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideOrderDatabase (app : Application) : OrderDatabase {
        return Room.databaseBuilder(
            app,
            OrderDatabase::class.java,
            "order_history"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOrderRepository(db : OrderDatabase) : OrderRepository {
        return OrderRepositoryImpl(db.orderDao)
    }




}
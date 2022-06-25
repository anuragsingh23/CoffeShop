package com.example.coffeeshop.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeeshop.data.data_source.OrderDatabase
import com.example.coffeeshop.data.repository.OrderRepositoryImpl
import com.example.coffeeshop.domain.repo.OrderRepository
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
    fun providesOrderDatabase(@ApplicationContext context: Context) : OrderDatabase{
        return Room.databaseBuilder(
            context,
            OrderDatabase::class.java,
            "orders_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesOrderRepository(db: OrderDatabase ) : OrderRepository {
        return OrderRepositoryImpl(db.orderDao())
    }
}
package com.bryan.miprimeraapp1.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bryan.miprimeraapp1.model.database.dao.UserDao
import com.bryan.miprimeraapp1.model.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {
    abstract fun getUserDao():UserDao
}
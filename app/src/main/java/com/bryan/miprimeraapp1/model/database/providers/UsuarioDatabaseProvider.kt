package com.bryan.miprimeraapp1.model.database.providers

import android.content.Context
import androidx.room.Room
import com.bryan.miprimeraapp1.model.database.UsuarioDatabase

object UsuarioDatabaseProvider {
    fun getDatabase(context: Context): UsuarioDatabase {
        return Room.databaseBuilder(
                context,
                UsuarioDatabase::class.java,
                "app_database"
            ).build()
    }
}
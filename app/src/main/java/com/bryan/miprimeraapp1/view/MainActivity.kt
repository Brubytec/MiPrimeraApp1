package com.bryan.miprimeraapp1.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bryan.miprimeraapp1.databinding.ActivityMainBinding
import com.bryan.miprimeraapp1.model.database.dao.UserDao
import com.bryan.miprimeraapp1.model.database.entities.UserEntity
import com.bryan.miprimeraapp1.model.database.providers.UsuarioDatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // Declaración de variables de binding y adaptador
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioAdapter: UsuarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Activar diseño de pantalla completa
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Establecer el contenido de la vista

        // Obtener la instancia de la base de datos
        val db = UsuarioDatabaseProvider.getDatabase(binding.root.context)
        val usuarioDao = db.getUserDao()

        // Inicializar los usuarios
        val listaUsers: List<UserEntity> = listOf(
            UserEntity(nombre = "Bryan ", apellido = "Gaviria"),
            UserEntity(nombre = "Carol", apellido = "Erazo"),
            UserEntity(nombre = "Angie", apellido = "Gaviria")
        )

        // Inserción de usuarios en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = db.getUserDao().insertAllUsers(listaUsers)
        }

        // Inicializa el adaptador con una lista vacía y configúralo en la vista de lista
        usuarioAdapter = UsuarioAdapter(this, mutableListOf())
        binding.lvUsers.adapter = usuarioAdapter

        // Cargar los usuarios desde la base de datos
        cargarUsuarios()
    }

    // Función para obtener todos los usuarios de la base de datos
    private fun obtenerUsuarios(usuarioDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            val usuarios = usuarioDao.getAllUsers()
            withContext(Dispatchers.Main) {
                usuarios.forEach { usuario ->
                    println("Usuario: ${usuario.nombre}, Apellido: ${usuario.apellido}")
                }
            }
        }
    }

    // Función para cargar usuarios en el adaptador desde la base de datos
    private fun cargarUsuarios() {
        val usuarioDao = UsuarioDatabaseProvider.getDatabase(applicationContext).getUserDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val usuarios = usuarioDao.getAllUsers()
            // Actualizar la lista en el hilo principal
            withContext(Dispatchers.Main) {
                usuarioAdapter.clear()
                usuarioAdapter.addAll(usuarios)
                usuarioAdapter.notifyDataSetChanged()
            }
        }
    }

    // Función para eliminar todos los usuarios de la base de datos
    private fun eliminarUsuarios(usuarioDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            usuarioDao.eliminarTodos()
        }
    }
}

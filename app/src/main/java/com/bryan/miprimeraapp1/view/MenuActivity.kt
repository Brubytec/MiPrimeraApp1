package com.bryan.miprimeraapp1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bryan.miprimeraapp1.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val buttonCamera: Button = findViewById(R.id.button_camera)
        val buttonGrabadora: Button = findViewById(R.id.button_grabadora)
        val buttonImagenCapturada: Button = findViewById(R.id.button_imagen_capturada)
        val buttonMain: Button = findViewById(R.id.button_main)

        buttonCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        buttonGrabadora.setOnClickListener {
            val intent = Intent(this, GrabadoraActivity::class.java)
            startActivity(intent)
        }

        buttonImagenCapturada.setOnClickListener {
            val intent = Intent(this, ImagenCapturadaActivity::class.java)
            startActivity(intent)
        }

        buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

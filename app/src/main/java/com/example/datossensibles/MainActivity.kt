package com.example.datossensibles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

// Se hace el llamado de las variables designadas en el activityMain

    private lateinit var tvRedirectSignUp: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Se crea el objeto de Autentificacion en FireBase
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // llamanos a la variables
        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etSEmailAddress)
        etPass = findViewById(R.id.etSConfPassword)

        // inicializando el objeto de autenticación de Firebase
        auth = FirebaseAuth.getInstance()

        // se le asigna el evento onclick al boton

        btnLogin.setOnClickListener {
            login()
        }

        // se asigna el evento Onclick a el TextView para camciar de actividad
        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, NuevoUsuario::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }


    private fun login() {
        // hacemos el llamado de las variable  etEmail, etPass
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()


        // validamos los espacios en blaco

        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Debe de ingresar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        // mostrar los mensajes de avertencia en caso que sea exitosa o fallida


        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Acceso Autorizado!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                // using finish() to end the activity
                finish()

            } else
                Toast.makeText(this, "¡Acceso no autorizado! ¡Correo electronico o contraseña incorrecta! ", Toast.LENGTH_SHORT).show()



        }
    }


}
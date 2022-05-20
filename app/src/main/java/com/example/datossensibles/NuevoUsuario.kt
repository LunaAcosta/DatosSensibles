package com.example.datossensibles

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NuevoUsuario : AppCompatActivity() {

    // hacemos el llamado de las variable

    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    // se asigna el objeto  de Autentificacion en FireBase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        // Llamado de las variables
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // iniciamos la autentificacion en FireBase
        auth = Firebase.auth

        // Asignamos el evento al onclick al boton

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // se asigna el evento onclick para cambiar de activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    // se crea una metodo para la validacion de los campus

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // se valida los campos en blanco
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Debe de ingresar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "El campo *Contraseña* debe coincidir con el campo *Confirmar contraseña*", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isNotBlank() || pass.isNotBlank() || confirmPassword.isNotBlank()){

            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show()
        }



        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //si el inicio de secion es correcto que lo identifique en el Registro de usuario
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // si la verificacion es incorrecta que envie un mensaje
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Autentificacion fallida.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}
package com.almadevs.androidcurso

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import com.almadevs.androidcurso.asistenciaapp.HomeActivity
import androidx.appcompat.widget.AppCompatEditText
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.almadevs.androidcurso.asistenciaapp.HomePrivilegeActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonEntrar = findViewById<Button>(R.id.buttonEntrar)
        buttonEntrar.setOnClickListener {
            val usuario = findViewById<EditText>(R.id.ediTextUsuario).text.toString()
            val contrasena = findViewById<EditText>(R.id.editTextPassword).text.toString()

            if (validateLogin(usuario, contrasena)) {
                val userType = getUserType(usuario)

                if (userType == "Usuario Privilegiado") {
                    navigateToHomePrivilige()
                } else if (userType == "Usuario Normal") {
                    navigateToHome()
                } else {
                    // Mostrar Snackbar cuando el inicio de sesión no es válido
                    Snackbar.make(buttonEntrar, "Usuario no válido", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        val editTextPassword: AppCompatEditText = findViewById(R.id.editTextPassword)

        editTextPassword.setOnTouchListener { _, event ->
            //Verifica si se hizo click en el drawable
            if (event.action == MotionEvent.ACTION_UP &&
                event.rawX >= (editTextPassword.right - editTextPassword.compoundDrawablesRelative[2].bounds.width())
            ) {
                // Realiza la acción de mostrar/ocultar la contraseña
                togglePasswordVisibility(editTextPassword)
                return@setOnTouchListener true
            }
            false
        }
    }

    //funcion de prueba previa a base de datos, sustituir al crear la base de datos
    private fun getUserType(usuario: String): String {
        return when (usuario) {
            "97250015" -> "Usuario Privilegiado"
            "90013743" -> "Usuario Normal"
            else -> "Desconocido"
        }
    }

    //funcion de prueba previa a base de datos, sustituir al crear la base de datos
    private fun validateLogin(usuario: String, contrasena: String): Boolean {
        // Información predefinida de usuarios
        val usuarios = mapOf(
            "97250015" to "Am172902",
            "90013743" to "Alma1234"
        )
        // Verificar si el usuario existe y la contraseña coincide
        return usuarios.containsKey(usuario) && usuarios[usuario] == contrasena
    }

    private fun navigateToHomePrivilige() {
        val intent = Intent(this, HomePrivilegeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun togglePasswordVisibility(editText: EditText) {
        // Cambia dinámicamente el tipo de entrada del EditText entre textPassword y text
        val isPasswordVisible = editText.transformationMethod is PasswordTransformationMethod

        if (isPasswordVisible) {
            // Si la contraseña es visible, ocultarla
            editText.transformationMethod = null
        } else {
            // Si la contraseña está oculta, mostrarla
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        // Mueve el cursor al final del texto después de cambiar la visibilidad
        editText.setSelection(editText.text.length)
    }

}

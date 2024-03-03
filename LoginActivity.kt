package com.almadevs.androidcurso

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.text.method.PasswordTransformationMethod
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Button
import com.almadevs.androidcurso.asistenciaapp.HomeActivity
import androidx.appcompat.widget.AppCompatEditText
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.almadevs.androidcurso.utils.Validator
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.almadevs.androidcurso.asistenciaapp.HomePrivilegeActivity
import com.almadevs.androidcurso.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
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
                } else if(!Validator.isNumeroEmpleado(usuario)){
                    snackBarMessage(R.string.enter_employee)
                    binding.ediTextUsuario.requestFocus() // Mostrar Snackbar cuando el inicio de sesión no es válido
                }else if(!Validator.validatePasswordLength(contrasena)){
                    snackBarMessage(R.string.enter_password)
                    binding.editTextPassword.requestFocus() // Mostrar Snackbar cuando el inicio de sesión no es válido
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
        val loginValido = usuarios.containsKey(usuario) && usuarios[usuario] == contrasena

        // Mostrar un Snackbar si el inicio de sesión no es válido
        if (!loginValido) {
            snackBarMessage("Verifica tu número de empleado o contraseña e intenta nuevamente")
        }

        return loginValido
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

    private fun snackBarMessage(message: Any) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            "",
            Snackbar.LENGTH_LONG
        )
        val params = snackbar.view.layoutParams as ViewGroup.MarginLayoutParams
        val resources = resources
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            36f,
            resources.displayMetrics
        ).toInt()
        params.setMargins(49, 0, 49, margin)
        snackbar.view.layoutParams = params
        val shapeDrawable = GradientDrawable().apply {
            cornerRadius =
                resources.getDimension(R.dimen.default_radius) // Radio de las esquinas redondeadas
            setColor(
                ContextCompat.getColor(
                    this@LoginActivity,
                    R.color.color_alert_message_login
                )
            )
            setStroke(
                resources.getDimensionPixelSize(R.dimen.snackbar_border_width),
                ContextCompat.getColor(this@LoginActivity, R.color.color_boder_alert_message_login)
            )
        }

        snackbar.view.background = shapeDrawable

        val inflater = LayoutInflater.from(this@LoginActivity)
        val customLayout = inflater.inflate(R.layout.layout_image_snackbar_login, null)
        val textMessageView = customLayout.findViewById<TextView>(R.id.textViewSnackbarLogin)

        when (message) {
            is Int -> textMessageView.setText(message)
            is String -> textMessageView.text = message
        }

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

        snackbarLayout.addView(customLayout, 0)
        snackbar.show()
    }

}

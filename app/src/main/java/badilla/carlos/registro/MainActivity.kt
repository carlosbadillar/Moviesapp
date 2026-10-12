package badilla.carlos.registro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etFechaNacimiento: EditText
    private lateinit var btnRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        val tvOlvidePassword = findViewById<TextView>(R.id.tvOlvidePassword)

        btnRegistrarse.setOnClickListener {
            validarYRegistrar()
        }

        tvOlvidePassword.setOnClickListener {
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarYRegistrar() {
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val fechaNacimientoStr = etFechaNacimiento.text.toString().trim()

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fechaNacimientoStr.isEmpty()) {
            Toast.makeText(this, "Error: Ningún campo puede quedar vacío", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Error: Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val edad = calcularEdad(fechaNacimientoStr)
        if (edad == null) {
            Toast.makeText(this, "Error: Formato de fecha inválido (Usa AAAA-MM-DD)", Toast.LENGTH_SHORT).show()
            return
        }

        if (edad < 18) {
            Toast.makeText(this, "Error: Debes ser mayor de 18 años para registrarte", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_LONG).show()
    }

    private fun calcularEdad(fechaStr: String): Int? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val fechaNacimiento = LocalDate.parse(fechaStr, formatter)
            val fechaActual = LocalDate.now()
            Period.between(fechaNacimiento, fechaActual).years
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
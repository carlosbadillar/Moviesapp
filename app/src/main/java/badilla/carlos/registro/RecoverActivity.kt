package badilla.carlos.registro

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecoverActivity : AppCompatActivity() {

    private lateinit var etCorreoRecuperar: EditText
    private lateinit var btnEnviarEnlace: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        etCorreoRecuperar = findViewById(R.id.etCorreoRecuperar)
        btnEnviarEnlace = findViewById(R.id.btnEnviarEnlace)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnEnviarEnlace.setOnClickListener {
            val correo = etCorreoRecuperar.text.toString().trim()
            if (correo.isEmpty()) {
                Toast.makeText(this, "Error: Ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Se ha enviado el enlace de recuperación a su correo", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}
package dev.hcartagena.pc2_cartagena

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val db = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()

            db.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"acceso permitido", Toast.LENGTH_LONG).show()
                        startActivity( Intent(this,MainActivity::class.java))
                    }else{
                        Toast.makeText(this,"EL USUARIO Y/O CLAVE NO EXISTE EN EL SISTEMA", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
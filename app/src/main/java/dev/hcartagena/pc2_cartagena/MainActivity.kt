package dev.hcartagena.pc2_cartagena

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val db = FirebaseFirestore.getInstance()
        val tvemail: TextView = findViewById(R.id.tvemail)
        val tvnombre: TextView = findViewById(R.id.tvnombre)
        val tvpass: TextView = findViewById(R.id.tvpass)
        val tvrepass: TextView = findViewById(R.id.tvrepass)

        db.collection("users")
            .addSnapshotListener{ snapshots, e->
                if(e!=null){
                    Log.w("Firebase","errorrrrrr", e)
                    return@addSnapshotListener
                }

                for(dc in snapshots!!.documentChanges){

                    when(dc.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> {
                            tvemail.text = dc.document.data["email"].toString()
                            tvnombre.text = dc.document.data["nombre"].toString()
                            tvpass.text = dc.document.data["password"].toString()
                            tvrepass.text = dc.document.data["repassword"].toString()

                        }


                        else -> {}
                    }
                }

            }
    }
}
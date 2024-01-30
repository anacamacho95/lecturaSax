package com.example.lecturasax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lecturasax.Dao.DaoSAX

class MainActivity : AppCompatActivity() {

    var trabajadores= mutableListOf<Trabajador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var daoSAX=DaoSAX(applicationContext)
        daoSAX.procesarArchivoAssetsXMLSAX()
    }
}
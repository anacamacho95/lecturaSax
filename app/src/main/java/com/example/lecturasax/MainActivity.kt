package com.example.lecturasax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {

    var trabajadores= mutableListOf<Trabajador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        procesarArchivoXMLSAX()

    }
    private fun procesarArchivoXMLSAX() {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = TrabajadorHandlerXML()

            val inputStream = assets.open("trabajadores.xml")
            parser.parse(inputStream, handler)

            // Accede a la lista de profesores desde handler.trabajadores
            handler.trabajadores.forEach {
                Log.d("SAX", "Trabajador: ${it.toString()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
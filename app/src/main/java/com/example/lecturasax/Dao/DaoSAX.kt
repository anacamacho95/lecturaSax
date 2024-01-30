package com.example.lecturasax.Dao

import android.content.Context
import android.util.Log
import com.example.lecturasax.TrabajadorHandlerXML
import javax.xml.parsers.SAXParserFactory

class DaoSAX(private val context: Context) {

    fun procesarArchivoAssetsXMLSAX() {
        try {
            // Crea una instancia de la fábrica de SAXParser
            val factory = SAXParserFactory.newInstance()

            // Crea un nuevo objeto SAXParser a partir de la fábrica
            val parser = factory.newSAXParser()

            // Crea un objeto de tu clase personalizada que extiende DefaultHandler (RutaHandler)
            val handler = TrabajadorHandlerXML()

            // Abre un flujo de entrada para leer el archivo XML desde la carpeta 'assets'
            val inputStream = context.assets.open("trabajadores.xml")

            // Parsea el archivo XML utilizando el handler personalizado
            parser.parse(inputStream, handler)

            // Accede a la lista de trabajadores desde el handler (RutaHandler)
            handler.trabajadores.forEach {
                // Imprime información sobre cada trabajador en el archivo XML
                Log.d("SAX", "Trabajador: ${it.nombre} Edad: ${it.edad}")
            }
        } catch (e: Exception) {
            // Maneja las excepciones imprimiendo la traza en la consola
            e.printStackTrace()
        }
    }


}
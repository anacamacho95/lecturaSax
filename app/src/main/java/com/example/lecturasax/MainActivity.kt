package com.example.lecturasax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lecturasax.Dao.DaoSAX
import org.simpleframework.xml.core.Persister
import java.io.*

class MainActivity : AppCompatActivity() {

    var trabajadores= mutableListOf<Trabajador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var daoSAX=DaoSAX(applicationContext)
        daoSAX.procesarArchivoAssetsXMLSAX()

        copiarArchivoDesdeAssets()
        procesarArchivoAssetsXML()
        Log.d("assetsXML", "probando procesado con Simple XML Framework")
        //xml simple, sin trabajadores nuevos
        trabajadores.forEach {
            Log.d("assetsXML", it.nombre)
        }

        //xml con trabajadores nuevos
        val trabajador=Trabajador("Pablo")
        addTrabajador(trabajador)
        ProcesarArchivoXMLInterno()

        trabajadores.forEach {
            Log.d("assetsPablo", it.nombre)
        }



    }

    private fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "trabajadores.xml"
        val archivoEnAssets = assets.open(nombreArchivo)
        val archivoInterno = openFileOutput(nombreArchivo, MODE_PRIVATE)

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()

    }

    private fun procesarArchivoAssetsXML() {
        val serializer = Persister()
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            inputStream = assets.open("trabajadores.xml")
            reader = InputStreamReader(inputStream)
            val trabajadoresListType = serializer.read(Trabajadores::class.java, reader, false)
            trabajadores.addAll(trabajadoresListType.trabajador)
        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun addTrabajador(trabajador: Trabajador) {
        try {
            val serializer = Persister()
            trabajadores.add(trabajador)
            val trabajadoresList = Trabajadores(trabajadores)
            val outputStream = openFileOutput("trabajadores.xml", MODE_PRIVATE)
            serializer.write(trabajadoresList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
    }

    fun ProcesarArchivoXMLInterno() {
        val nombreArchivo = "trabajadores.xml"
        val serializer = Persister()

        try {
            // Abrir el archivo para lectura
            val file = File(filesDir, nombreArchivo)
            val inputStream = FileInputStream(file)
            val trabajadoresList = serializer.read(Trabajadores::class.java, inputStream)
            trabajadores.addAll(trabajadoresList.trabajador)
            inputStream.close()
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

}
package com.example.lecturasax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lecturasax.Dao.DaoAssets
import com.example.lecturasax.Dao.DaoSAX
import org.simpleframework.xml.core.Persister
import java.io.*

class MainActivity : AppCompatActivity() {

    var trabajadores= mutableListOf<Trabajador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1º forma lectura: probando SAX
        Log.d("XMLSAX", "probando SAX")
        var daoSAX=DaoSAX(applicationContext)
        daoSAX.procesarArchivoAssetsXMLSAX()
        Log.d("XMLSAX", "SAX terminado")

        //2º forma lectura: probando lectura simple de carpeta Assets desde el DAO
        Log.d("SimpleXML", "probando assets XML")
        var daoAssets=DaoAssets(applicationContext)
        daoAssets.procesarArchivoAssetsXML()
        Log.d("SimpleXML", "probando procesado con Simple XML Framework")

        //tambien podemos trabajar con la lista de trabajadores del DAO desde el MAIN
        daoAssets.trabajadores.forEach(){
            Log.d("trabajadores", "NoM: ${it.nombre}")
        }

        //copio assets y lo pego en el archivo interno
        daoAssets.copiarArchivoDesdeAssets()


        //xml con trabajadores nuevos
        val trabajador=Trabajador("Pablo")
        daoAssets.addTrabajador(trabajador)

        daoAssets.ProcesarArchivoXMLInterno()




    }

    /* TRABAJAR CON XML DESDE EL MAIN

     private fun procesarArchivoXMLSAX() {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = ProfesorHandlerXML()

            val inputStream = assets.open("profesores.xml")
            parser.parse(inputStream, handler)

            // Accede a la lista de profesores desde handler.profesores
            handler.profesores.forEach {
                Log.d("SAX", "Profesor: ${it.nombre}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    private fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "trabajadores.xml"
        val archivoEnAssets = assets.open(nombreArchivo)
        val archivoInterno = openFileOutput(nombreArchivo, MODE_PRIVATE)

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()

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
    */
}
package com.example.lecturasax

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class TrabajadorHandlerXML : DefaultHandler() {
    private val cadena = StringBuilder()
    private var trabajador: Trabajador? = null
    var trabajadores: MutableList<Trabajador> = mutableListOf()

    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        trabajadores = mutableListOf()
        Log.d("SAX", "abriendo el documento")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, nombreLocal: String, nombre: String, attributes: Attributes) {
        cadena.setLength(0)
        if (nombre == "trabajador") {
            trabajador = Trabajador()
            trabajador?.empresa = attributes.getValue("empresa")
            trabajador?.lugar = attributes.getValue("lugar")
        }
        Log.d("SAX", "abriendo etiqueta trabajador")
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        Log.d("SAX", "guardando en una cadena $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "nombre" -> trabajador?.nombre = cadena.toString()
            "edad" -> trabajador?.edad = cadena.toString().toInt()
            "trabajador" -> trabajador?.let { trabajadores.add(it) }
        }

        Log.d("SAX", "cerrando elemento $nombre $nombreLocal")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        Log.d("SAX", "Documento Terminado")
    }
}

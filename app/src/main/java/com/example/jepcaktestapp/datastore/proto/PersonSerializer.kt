package com.example.jepcaktestapp.datastore.proto

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object PersonSerializer :Serializer<PersonProtos.Person>{

    override val defaultValue: PersonProtos.Person
        get() = PersonProtos.Person.getDefaultInstance()

    override fun readFrom(input: InputStream): PersonProtos.Person {
        try {
            return PersonProtos.Person.parseFrom(input)
        }catch (e:IOException){
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override fun writeTo(t: PersonProtos.Person, output: OutputStream) =  t.writeTo(output)
}
package com.danielqueiroz.libraryshop.integration.controller.withyml.mapper

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.type.TypeFactory
import io.restassured.mapper.ObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper as JacksonObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import io.restassured.mapper.ObjectMapperDeserializationContext
import io.restassured.mapper.ObjectMapperSerializationContext

class YMLMapper : ObjectMapper {

    private val objectMapper: JacksonObjectMapper = JacksonObjectMapper(YAMLFactory())
    private val typeFactory: TypeFactory = TypeFactory.defaultInstance()

    init {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    override fun deserialize(mapper: ObjectMapperDeserializationContext): Any? {
        try {
            val dataToSerialize = mapper.dataToDeserialize.asString()
            val type = mapper.type as Class<*>
            return objectMapper.readValue(dataToSerialize, typeFactory.constructType(type))
        } catch (e: JsonMappingException) {
            e.printStackTrace()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }

    override fun serialize(mapper: ObjectMapperSerializationContext): Any? {
        try {
            return objectMapper.writeValueAsString(mapper.objectToSerialize)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

        return null
    }

}
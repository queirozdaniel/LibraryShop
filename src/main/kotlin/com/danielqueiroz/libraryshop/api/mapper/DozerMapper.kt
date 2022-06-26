package com.danielqueiroz.libraryshop.api.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import java.util.stream.Collectors

object DozerMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O,D> parseObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }

    fun <O,D> parseListObjects(origin: List<O>, destination: Class<D>?): List<D> {
        return origin.map { mapper.map(it, destination) }
    }

}
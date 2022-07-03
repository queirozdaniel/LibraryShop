package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.config.util.MediaType
import com.danielqueiroz.libraryshop.api.data.vo.v1.BookVO
import com.danielqueiroz.libraryshop.domain.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books/v1")
class BookController(
    private val service: BookService
) {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun getBookById(@PathVariable id: Long): BookVO{
        return service.findById(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun getAllBooks(): List<BookVO> {
        return service.findAll()
    }

}
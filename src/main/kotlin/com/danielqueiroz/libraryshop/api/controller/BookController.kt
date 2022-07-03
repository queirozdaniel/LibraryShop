package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.config.util.MediaType
import com.danielqueiroz.libraryshop.api.data.vo.v1.BookVO
import com.danielqueiroz.libraryshop.api.openapi.BookControllerOpenAPI
import com.danielqueiroz.libraryshop.domain.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books/v1")
class BookController(
    private val service: BookService
) : BookControllerOpenAPI {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun getBookById(@PathVariable id: Long): BookVO{
        return service.findById(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun getAllBooks(): List<BookVO> {
        return service.findAll()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun createBook(@RequestBody bookVO: BookVO): BookVO{
        return service.create(bookVO)
    }

    @PutMapping("/{id}",consumes = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun updateBook(@RequestBody bookVO: BookVO, @PathVariable id: Long): BookVO{
        bookVO.key = id
        return service.update(bookVO)
    }

    @DeleteMapping("/{id}")
    override fun deleteBook(@PathVariable id: Long) : ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }


}
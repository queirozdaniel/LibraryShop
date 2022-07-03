package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.api.controller.BookController
import com.danielqueiroz.libraryshop.api.data.vo.v1.BookVO
import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import com.danielqueiroz.libraryshop.api.mapper.DozerMapper
import com.danielqueiroz.libraryshop.domain.repository.BookRepository
import com.danielqueiroz.libraryshop.domain.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookServiceImpl : BookService {

    @Autowired
    private lateinit var repository: BookRepository

    override fun findById(id: Long): BookVO {
        val book = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        val bookVO = DozerMapper.parseObject(book, BookVO::class.java)

        val withSelfRel = WebMvcLinkBuilder.linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)

        return bookVO
    }

    override fun findAll(): List<BookVO> {
        val books = repository.findAll()
        val vos = DozerMapper.parseListObjects(books, BookVO::class.java)
        vos.forEach { it.add(WebMvcLinkBuilder.linkTo(BookController::class.java).slash(it.key).withSelfRel()) }
        return vos
    }

    @Transactional
    override fun create(book: BookVO): BookVO {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun update(book: BookVO?): BookVO {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

}
package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.domain.repository.BookRepository
import com.danielqueiroz.libraryshop.domain.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {

    @Autowired
    private lateinit var repository: BookRepository

    override fun findById() {
        TODO("Not yet implemented")
    }

}
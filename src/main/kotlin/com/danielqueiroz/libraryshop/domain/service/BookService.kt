package com.danielqueiroz.libraryshop.domain.service

import com.danielqueiroz.libraryshop.api.data.vo.v1.BookVO

interface BookService {
    fun findById(id: Long): BookVO
    fun findAll(): List<BookVO>
    fun create(book: BookVO): BookVO
    fun update(book: BookVO?): BookVO
    fun delete(id: Long)
}
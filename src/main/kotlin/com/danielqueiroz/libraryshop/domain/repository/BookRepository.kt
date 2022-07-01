package com.danielqueiroz.libraryshop.domain.repository

import com.danielqueiroz.libraryshop.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long?> {
}
package com.danielqueiroz.libraryshop.mockito.domain.service

import com.danielqueiroz.libraryshop.domain.model.Book
import com.danielqueiroz.libraryshop.domain.repository.BookRepository
import com.danielqueiroz.libraryshop.domain.service.impl.BookServiceImpl
import com.danielqueiroz.libraryshop.unittests.mapper.mocks.MockBook
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
@ExtendWith(SpringExtension::class)
class BookServicesTest {

    private var input: MockBook? = null

    @MockBean
    private lateinit var repository: BookRepository

    @InjectMocks
    private lateinit var service: BookServiceImpl

    @BeforeEach
    fun setupMock() {
        input = MockBook()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `find all books`() {
        val list: List<Book> = input!!.mockEntityList()
        `when`(repository.findAll()).thenReturn(list)
        val books = service.findAll()
        assertNotNull(books)
        assertEquals(14, books.size)
        val bookOne = books[1]
        assertNotNull(bookOne)
        assertNotNull(bookOne.key)
        assertNotNull(bookOne.links)
        assertTrue(bookOne.links.toString().contains("/api/books/v1/1"))
        assertEquals("Some Author1", bookOne.author)
        assertEquals("Some Title1", bookOne.title)
        assertEquals(25.0, bookOne.price)

        val bookFour = books[4]
        assertNotNull(bookFour)
        assertNotNull(bookFour.key)
        assertNotNull(bookFour.links)
        assertTrue(bookFour.links.toString().contains("/api/books/v1/4"))
        assertEquals("Some Author4", bookFour.author)
        assertEquals("Some Title4", bookFour.title)
        assertEquals(25.0, bookFour.price)

        val bookSeven = books[7]
        assertNotNull(bookSeven)
        assertNotNull(bookSeven.key)
        assertNotNull(bookSeven.links)
        assertTrue(bookSeven.links.toString().contains("/api/books/v1/7"))
        assertEquals("Some Author7", bookSeven.author)
        assertEquals("Some Title7", bookSeven.title)
        assertEquals(25.0, bookSeven.price)
    }

    @Test
    fun `return one book by informed id`() {
        val id = 1
        val book = input!!.mockEntity(id)
        book.id = id.toLong()
        `when`(repository.findById(1L)).thenReturn(Optional.of(book))
        val result = service.findById(1L)
        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/books/v1/$id"))
        assertEquals(book.author, result.author)
        assertEquals(book.title, result.title)
        assertEquals(book.price, result.price)
    }

}
package com.danielqueiroz.libraryshop.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var author: String = "",

    @Column(name = "launch_date")
    var launchDate: LocalDateTime? = null,

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(nullable = false, length = 280)
    var title: String = "",
)

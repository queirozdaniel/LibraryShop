package com.danielqueiroz.libraryshop.domain.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "permission")
class Permission : GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "description", nullable = true)
    var description: String? = ""

    override fun getAuthority() = description!!

}
package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserServiceImpl(
    @field:Autowired var repository: UserRepository
) : UserDetailsService {

    private val logger = Logger.getLogger(UserServiceImpl::class.java.name)

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Finding one User by Username $username")
        val user = repository.findByUsername(username)
        return user ?: throw UsernameNotFoundException("Username $username not found")
    }

}
package com.example.testfordatabase.security

import com.example.testfordatabase.AuthenticationService
import com.example.testfordatabase.user.MyUser
import com.example.testfordatabase.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpringAuthenticationService @Autowired constructor(
    private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override val currentMyUser: Optional<MyUser?>?
        get() =
           if( SecurityContextHolder.getContext()?.authentication?.principal  == "anonymousUser"){
               null
           }else{
               val ud = SecurityContextHolder.getContext().authentication.principal as UserDetails
               userRepository.findByEmail(ud.username)
           }

    override val currentToken: Optional<String>?
        get() = Optional.ofNullable(SecurityContextHolder.getContext()?.authentication?.credentials as? String) ?:null

    override fun authenticate(email: String?, password: String?): Optional<MyUser?>? {
       return userRepository
            .findByEmail(email)?.flatMap {user ->
                if (passwordEncoder.matches(password, user?.passwordHash)) {
                    return@flatMap Optional.of(user!!)
                } else {
                    return@flatMap Optional.empty<MyUser>()
                }
            }
    }

    override fun encodePassword(password: String?): String? {
        return passwordEncoder.encode(password)
    }
}
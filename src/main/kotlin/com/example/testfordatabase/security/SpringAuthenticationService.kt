package com.example.testfordatabase.security

import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.aggregate.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SpringAuthenticationService @Autowired constructor(
    private val userRepository: UserRepository//, private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override val currentMyUser: MyUser?
        get() = curUser
//           if( SecurityContextHolder.getContext()?.authentication?.principal  == "anonymousUser"){
//               null
//           }else{
//               val ud = SecurityContextHolder.getContext()?.authentication?.principal as? UserDetails
//               userRepository.findByEmail(ud?.username)
//           }

    override val currentToken: String?
        get() = "(SecurityContextHolder.getContext()?.authentication?.credentials as? String) ?:null"

    var curUser: MyUser? = null
    override fun authenticate(email: String?, password: String?): MyUser? {
       return userRepository
            .findByEmail(email)?.let {user ->
//                if (passwordEncoder.matches(password, user.passwordHash)) {
                    curUser = user
                    return user
//                } else {
//                    return null
//                }
            }
    }

    override fun encodePassword(password: String?): String? {
        return "passwordEncoder.encode(password)"
    }
}
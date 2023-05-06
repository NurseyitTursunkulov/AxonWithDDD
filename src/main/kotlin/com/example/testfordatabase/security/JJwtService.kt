package com.example.testfordatabase.security

import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.aggregate.user.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class JJwtService
/**
 * Creates DefaultJwtService instance.
 *
 * @param secret jwt secret
 * @param sessionTime jwt session time in seconds
 * @param userRepository user repository
 */ @Autowired constructor(
    @param:Value("\${jwt.secret}") private val secret: String,
    @param:Value("\${jwt.sessionTime}") private val sessionTime: Int,
    private val userRepository: UserRepository
) : JwtService {
    /** {@inheritDoc}  */
    override fun generateToken(myUser: MyUser?): String? {
        return Jwts.builder()
            .setSubject(myUser?.id?.let { java.lang.Long.toString(it) })
            .setExpiration(Date(System.currentTimeMillis() + sessionTime * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    /** {@inheritDoc}  */
    override fun getUser(token: String?): Optional<MyUser?>? {
        return try {
            val subject = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
            val userId = subject.toLong()
            userRepository.findById(userId)
        } catch (e: Exception) {
           null
        }
    }
}

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

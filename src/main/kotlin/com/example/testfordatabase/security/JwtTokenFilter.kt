package com.example.testfordatabase.security

import com.example.testfordatabase.domain.aggregate.user.MyUser
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import java.util.function.Consumer

@Component
class JwtTokenFilter @Autowired constructor(private val jwtService: JwtService) : OncePerRequestFilter() {
    private fun getTokenString(header: String?): Optional<String> {
        return if (header == null) {
            Optional.empty()
        } else {
            val split = header.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split.size < 2) {
                Optional.empty()
            } else {
                Optional.ofNullable(split[1])
            }
        }
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        getTokenString(httpServletRequest.getHeader(AUTH_HEADER))
            .ifPresent { token: String? ->
                jwtService
                    .getUser(token)
                    ?.ifPresent(
                        Consumer { (_, email, _, passwordHash): MyUser ->
                            val ud = org.springframework.security.core.userdetails.User.withUsername(
                                email
                            )
                                .password(passwordHash)
                                .authorities(emptyList())
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .disabled(false)
                                .build()
                            val authenticationToken = UsernamePasswordAuthenticationToken(ud, token, ud.authorities)
                            authenticationToken.details =
                                WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                            SecurityContextHolder.getContext().authentication = authenticationToken
                        })
            }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}
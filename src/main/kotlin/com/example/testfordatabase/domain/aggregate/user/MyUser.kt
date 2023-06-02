package com.example.testfordatabase.domain.aggregate.user

import com.example.testfordatabase.security.JwtTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Service
import org.springframework.web.cors.CorsConfiguration
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.servlet.http.HttpServletRequest
import javax.persistence.*


//import com.google.common.base.MoreObjects;
@Entity
data class MyUser   (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    var email: String = "",
    val passwordHash: String = "",
    var bio: String? = null,
    var image: String? = null,
    @Column(name = "enabled")
    val enabled: Boolean = true,
    private var username: String = "",
    private val password:String = "",
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    @Transient
    val roles: Set<Role> = emptySet(),

    @Column(name = "account_non_expired")
    val accountNonExpired: Boolean = true,

    @Column(name = "credentials_non_expired")
    val credentialsNonExpired: Boolean = true,

    @Column(name = "account_non_locked")
    val accountNonLocked: Boolean = true
) : UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()

    override fun getPassword(): String = password

    fun setUsername(username: String){
        this.username = username
    }

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked

    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired

    override fun isEnabled(): Boolean = enabled


}

enum class Role : GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    override fun getAuthority(): String {
        return name
    }
}

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return user
    }
}



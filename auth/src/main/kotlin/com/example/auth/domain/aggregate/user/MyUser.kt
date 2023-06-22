package com.example.auth.domain.aggregate.user

//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
import javax.persistence.Column
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
     var username: String = "",
    private val password:String = "",
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "users_roles",
//        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
//        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
//    )
//    @Transient
//    val roles: Set<Role> = emptySet(),

    @Column(name = "account_non_expired")
    val accountNonExpired: Boolean = true,

    @Column(name = "credentials_non_expired")
    val credentialsNonExpired: Boolean = true,

    @Column(name = "account_non_locked")
    val accountNonLocked: Boolean = true
)  {



}

//enum class Role : GrantedAuthority {
//    ROLE_USER, ROLE_ADMIN;
//
//    override fun getAuthority(): String {
//        return name
//    }
//}

//@Service
//class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
//    override fun loadUserByUsername(username: String): UserDetails {
//        val user = userRepository.findByUsername(username)
//            ?: throw UsernameNotFoundException("User not found with username: $username")
//
//        return user
//    }
//}



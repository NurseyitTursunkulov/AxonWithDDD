//package com.example.security
//
//import com.example.testfordatabase.security.JwtTokenFilter
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpMethod
//import org.springframework.http.HttpStatus
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.HttpStatusEntryPoint
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import org.springframework.web.cors.CorsConfiguration
//
//@Configuration
//@EnableWebSecurity
//class UserSecurityConfig @Autowired constructor(
//    private val jwtTokenFilter: JwtTokenFilter
//){
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.cors()
//            .configurationSource { request: HttpServletRequest? ->
//                val cors = CorsConfiguration()
//                cors.allowedOrigins = listOf(
//                    "http://localhost:8080",
//                    "https://editor.swagger.io"
//                )
//                cors.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                cors.allowedHeaders = listOf("*")
//                cors
//            }
//            .and()
//            .csrf()
//            .disable()
//
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
//
//        http.exceptionHandling()
//            .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//            .and();
//
//
//        // Set permissions on endpoints
//        http.authorizeRequests()
//            .requestMatchers("/", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/api-docs")
//            .permitAll() // Our public endpoints
//            .requestMatchers(HttpMethod.POST, "/createuser", "/login")
//            .permitAll()
//            .requestMatchers(HttpMethod.GET, "/api/articles/**", "/api/profiles/**", "/api/tags")
//            .permitAll() // Our private endpoints
////            .anyRequest()
////            .authenticated()
//
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
//
//        return http.build()
//    }
//
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val userDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(userDetails)
//    }
//}
package br.com.dasa.fakeexcheduleapi.clients

import feign.RequestInterceptor
import feign.RequestTemplate
import org.keycloak.KeycloakSecurityContext
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class RulesClientConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun requestInterceptor(): RequestInterceptor? {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val context = getKeycloakSecurityContext()
            requestTemplate.header("Authorization", "Bearer " + context.tokenString)
        }
    }

    private fun getKeycloakSecurityContext(): KeycloakSecurityContext {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        val token: KeycloakAuthenticationToken
        val context: KeycloakSecurityContext
        checkNotNull(authentication) {
            "Cannot set authorization header because there is no authenticated principal"
        }
        check(KeycloakAuthenticationToken::class.java.isAssignableFrom(authentication.javaClass)) {
            "Cannot set authorization header because Authentication is of type ${authentication.javaClass} " +
                "but ${KeycloakAuthenticationToken::class.java} is required"
        }
        token = authentication as KeycloakAuthenticationToken
        context = token.account.keycloakSecurityContext
        return context
    }
}
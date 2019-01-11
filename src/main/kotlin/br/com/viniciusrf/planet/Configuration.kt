package br.com.viniciusrf.planet

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {

        val interceptor = ClientHttpRequestInterceptor { request, body, execution ->
            request.headers.add("user-agent", "spring")
            execution.execute(request, body)
        }

        return restTemplateBuilder.additionalInterceptors(interceptor).build()
    }
}
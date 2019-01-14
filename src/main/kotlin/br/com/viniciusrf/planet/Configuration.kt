package br.com.viniciusrf.planet

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

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

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.viniciusrf.planet.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Star Wars API")
                .version("1.0.0")
                .license("Apache 2.0")
                .build()
    }
}
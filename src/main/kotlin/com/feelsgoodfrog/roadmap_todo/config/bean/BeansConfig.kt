package com.feelsgoodfrog.roadmap_todo.config.bean

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val jsoupModule = SimpleModule().apply {
            addDeserializer(String::class.java, object : JsonDeserializer<String>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String {
                    val value = p.valueAsString
                    return Jsoup.clean(value, Safelist.basic())
                }
            })
        }

        val objectMapper = ObjectMapper()
        objectMapper.registerKotlinModule()
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objectMapper.registerModule(jsoupModule)
        return objectMapper
    }
}

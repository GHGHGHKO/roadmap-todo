package com.feelsgoodfrog.roadmap_todo.common.advice

import com.feelsgoodfrog.roadmap_todo.common.dto.AdviceResponseDto
import com.feelsgoodfrog.roadmap_todo.common.exception.UserExistsException
import com.feelsgoodfrog.roadmap_todo.common.exception.UserNotFoundException
import io.github.resilience4j.ratelimiter.RequestNotPermitted
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException

@RestControllerAdvice
class GlobalExceptionAdvice {

    @ExceptionHandler(
        value = [
            UserExistsException::class,
            IllegalArgumentException::class,
            UserNotFoundException::class,
            UsernameNotFoundException::class
        ]
    )
    @ResponseBody
    fun badRequestException(
        e: Exception
    ): ResponseEntity<AdviceResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.message?.let {
                AdviceResponseDto(
                    message = it
                )
            })
    }

    @ExceptionHandler(value = [IllegalAccessException::class])
    @ResponseBody
    fun forbiddenException(
        e: IllegalAccessException
    ): ResponseEntity<AdviceResponseDto> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                e.message?.let {
                    AdviceResponseDto(
                        message = it
                    )
                }
            )
    }

    @ExceptionHandler(value = [RequestNotPermitted::class])
    @ResponseBody
    fun reslience4jRequestNotPermitted(
        e: RequestNotPermitted
    ): ResponseEntity<AdviceResponseDto> {
        return ResponseEntity
            .status(HttpStatus.TOO_MANY_REQUESTS)
            .body(e.message?.let {
                AdviceResponseDto(
                    message = it
                )
            })
    }
}

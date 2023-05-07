package com.example.testfordatabase.application.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> ok(body: T): ResponseEntity<T>? {
    return ResponseEntity(body, HttpStatus.OK)
}
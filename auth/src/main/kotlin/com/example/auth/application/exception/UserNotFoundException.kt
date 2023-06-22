package com.example.auth.application.exception

import com.example.auth.application.exception.InvalidRequestException

class UserNotFoundException(message: String?) : InvalidRequestException(message)
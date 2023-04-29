package com.example.testfordatabase.exception

import com.example.testfordatabase.InvalidRequestException

class UsernameAlreadyUsedException(message: String?) : InvalidRequestException(message)
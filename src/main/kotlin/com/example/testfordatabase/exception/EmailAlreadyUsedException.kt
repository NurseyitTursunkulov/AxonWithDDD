package com.example.testfordatabase.exception

import com.example.testfordatabase.InvalidRequestException

class EmailAlreadyUsedException(message: String?) : InvalidRequestException(message)
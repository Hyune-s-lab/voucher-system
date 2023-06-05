package com.example.coredomain.support.exception

open class BusinessException(val errorType: ErrorType) : RuntimeException(errorType.message)

package com.feelsgoodfrog.roadmap_todo.common.exception

class UserExistsException(message: String): RuntimeException(message)
class UserNotFoundException(message: String): RuntimeException(message)
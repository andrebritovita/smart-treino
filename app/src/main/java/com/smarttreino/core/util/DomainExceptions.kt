package com.smarttreino.core.util

// Exceção para campos vazios
class EmptyFieldException : Exception()

// Exceção para senha curta
class PasswordTooShortException : Exception()

// Exceção para usuário não encontrado (Google)
class UserNotFoundException : Exception()

// Exceção de Email já existe
class EmailAlreadyInUseException : Exception()
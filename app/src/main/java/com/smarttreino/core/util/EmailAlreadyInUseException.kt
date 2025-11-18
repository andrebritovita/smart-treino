package com.smarttreino.core.util

import java.lang.Exception

/**
 * Exceção lançada quando um usuário tenta se cadastrar com um e-mail
 * que já foi associado a um provedor forte (Google, Apple).
 */
class EmailAlreadyInUseException : Exception(
    "Este e-mail já está em uso por outro método de login. Tente fazer login com Google."
)
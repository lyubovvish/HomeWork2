package com.example.firstlesson

import androidx.annotation.VisibleForTesting

object UserHolder {

    private val map = mutableMapOf<String, User>()
    private val phoneFormat = Regex("""^[+][\d]{11}""")

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists")
            else map[user.login] = user
        }

    fun loginUser(login: String, password: String): String? {
        val phoneLogin = cleanPhone(login)
        return if (phoneLogin.isNotEmpty()) {
            map[phoneLogin]
        } else {
            map[login.trim()]
        }?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }

   fun registerUserByPhone(fullName: String, rawPhone: String): User {
       return User.makeUser(fullName, phone = rawPhone)
           .also { user ->
               if (map.containsKey(user.phone))
                   throw IllegalArgumentException("phone used")
               if (cleanPhone(rawPhone).matches("^\\+?[0-9]{11}\$".toRegex()))
                   map[user.login] = user
               else
                   throw IllegalArgumentException("phone incorrect")
           }
    }

   fun requestAccessCode(login: String) {
       val cleanPhone = cleanPhone(login)
       map[cleanPhone]?.let {
           val code = it.generateAccessCode()
           it.sendAccessCodeToUser(it.phone!!, code)
           it.accessCode = code
           it.passwordHash = it.encrypt(code)
       }
   }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    private fun cleanPhone(phone: String): String {
        return phone.replace("""[^+\d]""".toRegex(), "")
    }
}
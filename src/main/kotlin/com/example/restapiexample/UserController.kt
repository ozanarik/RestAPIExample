package com.example.restapiexample
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController {
    private val users = mutableListOf(
        User(1, "Name1", 23),
        User(2, "Name2",25)
    )

    @GetMapping
    fun getAllUsers(): List<User> {
        return users
    }
    @PostMapping
    fun addUser(@RequestBody user: User): User {
        users.add(user)
        return user
    }
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User? {
        return users.find {
            it.id == id
        }
    }
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): User? {
        val userIndex = users.indexOfFirst{ it.id == id }
        if (userIndex != -1) {
            users[userIndex] = updatedUser
            return updatedUser
        }
        return null
    }
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): Boolean {
        return users.removeIf { it.id == id}
    }
}
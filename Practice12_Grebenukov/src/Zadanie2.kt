import kotlinx.coroutines.*
import java.lang.NumberFormatException

fun main() {
    println("Введите имя пользователя")
    var login = readLine()!!
    println("Введите пароль")
    var password = readLine()!!
    println("Введите количество участников")
    var countOfParticipants = readLine()!!.toInt()
    while (countOfParticipants <= 0) {
        try {
            println("Введите количество участников")
            countOfParticipants = readLine()!!.toInt()
            break
        } catch (e: NumberFormatException) {
            println("Введите количество участников")
        }
    }
    val repositories = Repositories()

    val usersAndRepos = runBlocking {
        repositories.GetUsersAndRepositories(countOfParticipants)
    }

    runBlocking {
        repositories.Block(false)
        println("Количество участников")
        usersAndRepos.sortedWith(compareBy({ it.first }, { it.second }))
            .forEach { user -> launch { repositories.PrintUser(user) } }
        delay(3000)
    }
}



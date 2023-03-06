import kotlinx.coroutines.*
class Repositories()
{
     fun Block(block: Boolean){
       while(block == false){
           println("Заблокировано. Введите 'Загрузить участников'")
           var otvet = readLine()!!
           if(otvet == "Загрузить участников"){
               break
           }

       }

    }
    suspend fun GetUsersAndRepositories(repCount: Int): List<Pair<Int, String>> = withContext(Dispatchers.IO)
    {
        val users = mutableListOf<Pair<Int, String>>()
        try {
            for(i in 1..repCount)
            {
                println("Введите имя участника №$i")
                val name: String = readLine()!!
                println("Введите количество его репозиториев")
                val repCount: Int = readLine()!!.toInt()
                users.add(repCount to name)
            }
        }
        catch (e: Exception)
        {
            println("Вы ввели не число")
        }
        users
    }
    suspend fun PrintUser(user: Pair<Int, String>)
    {
        delay(1000)
        println("${user.second  } - ${user.first} репозиториев")
    }

}
import kotlinx.coroutines.*
import kotlin.random.Random
fun main() = runBlocking {
    // Читаем количество функций, которые нужно выполнить
    println("Введите количество функций:")
    var count = readLine()?.toInt() ?: 0
    while(count <= 0){
        println("Количество функций должно быть больше нуля.")
        count = readLine()?.toInt() ?: 0
//        return@runBlocking
    }
    // Создаем список Deferred-объектов, каждый из которых запускает свою корутину и вызывает функцию getDataFromFunction()
    val tasks = List(count) { index -> async { getDataFromFunction(index) } }

    // Создаем список Deferred-объектов, каждый из которых запускает свою корутину и вызывает функцию performClick()
    val clicks = List(count) { index -> async { performClick(index) } }

    // Создаем  Deferred-объект, который запускает корутину и вызывает функцию longRunningOperation()
    val longRunningTask = async { longRunningOperation() }

    // Дожидаемся выполнения всех корутин из списка tasks и получаем результаты
    val results = tasks.awaitAll()

    // Дожидаемся выполнения всех корутин из списка clicks и получаем результаты
    val clickResults = clicks.awaitAll()

    //Дожидаемся выполнения корутины из longRunningTask и получаем результаты
    val longRunningTaskResult = longRunningTask.await()
    println("Результаты вызова функций:")
    println(results)

    println("Результаты кликов:")
    println(clickResults)

    println("Результат длительной операции: $longRunningTaskResult")
}

// Функция, которая возвращает индекс, умноженный на 2, с некоторой задержкой
suspend fun getDataFromFunction(index: Int): Int {

    delay(Random.nextLong(1000,3000)) // имитируем задержку
    return index * 2
}

// Функция, которая возвращает строку с сообщением о клике на кнопку, с некоторой задержкой
suspend fun performClick(index: Int): String {
    delay(Random.nextLong(1000,3000)) // имитируем задержку
    return "Кликнули на кнопку $index"
}

//Функция, которая выполняет длительную операцию в виде задержки на 5 секунд и возвращает рандомное значение
suspend fun longRunningOperation(): Int {
    delay(5000L) // имитируем длительную операцию
    return Random.nextInt(0, 100)
}



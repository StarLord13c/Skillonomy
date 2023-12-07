import scala.io.StdIn

// клас Teacher

class Teacher(fullName: String, age: Int, sex: String, var Coins: Int, var address: Adress, var course: Course) extends Human(fullName, age, sex) { // успадкування від класа Human 
  def displayInfo(): Unit = { // функція для виведення інформації
    println(s"$fullName, Вiк: $age, Стать: $sex, Coins: $Coins") 
    println(s"Адреса: ${address.city}")
    println(s"Курс: ${course.courseName}, Тривалiсть: ${course.month} мiсяцi(в)") // вивід даних про курс який веде викладач
  }
  def changeInfo(): Unit = { // функція для зміни інформації
    println(s"Змiнюємо даннi вчителя $fullName.")
    println("Введiть новий вiк вчителя:")
    val новийВік = StdIn.readInt()
    age = новийВік
    println("Введiть нову стать вчителя:")
    val новаСтать = StdIn.readLine()
    sex = новаСтать
    println("Введiть нову кiлькiсть монет вчителя:")
    val новаКількістьМонет = StdIn.readInt()
    Coins = новаКількістьМонет
    println("Введiть нове мiсто адреси вчителя:")
    val новеМісто = StdIn.readLine()
    address.city = новеМісто
    println(s"Данi вчителя $fullName були змiненi.")
  }
}

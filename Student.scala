import scala.io.StdIn

// клас Student

class Student(fullName: String, age: Int, sex: String, var сoins: Int, var address: Adress, var grades: Grades) extends Human(fullName, age, sex) { // успадкування від класа Human 
  def displayInfo(): Unit = { // функція для виводу інформації
    println(s"$fullName, Вiк: $age, Стать: $sex, Coins: $сoins")
    println(s"Адреса: ${address.city}")
  }
  def changeInfo(): Unit = { // функція для зміненя інформації
    println(s"Змiнюємо даннi студента $fullName.")
    println("Введiть новий вiк студента:")
    val новийВік = StdIn.readInt()
    age = новийВік
    println("Введiть нову стать студента:")
    val новаСтать = StdIn.readLine()
    sex = новаСтать
    println("Введiть нову кiлькість монет студента:")
    val новаКількістьМонет = StdIn.readInt()
    сoins = новаКількістьМонет
    println("Введiть нове місто адреси студента:")
    val новеМісто = StdIn.readLine()
    address.city = новеМісто
    println(s"Данi студента $fullName були зміненi.")
  }
}

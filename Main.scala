import scala.io.StdIn
import scala.util.Random

// головна програма

object Main {
  val random = new Random()
  val studentGrades = new Grades(List.fill(6)(random.nextInt(11))) // генерація випадкових оцінок
  
  var студенти: List[Student] = List( // лист студентів
    new Student("Alex Alic", 18, "male", 50, new Adress("Kherson"), studentGrades),
    new Student("Vlad Meme", 19, "male", 50, new Adress("Kherson"), studentGrades),
    new Student("Sergey Veron", 21, "male", 50, new Adress("Odesa"), studentGrades),
    new Student("Sasha Kolig", 18, "male", 50, new Adress("Kherson"), studentGrades),
    new Student("Hog Rask", 21, "male", 50, new Adress("Odesa"), studentGrades),
    new Student("Hog Rask1", 21, "female", 50, new Adress("Odesa"), studentGrades),
    new Student("Hog Rask2", 22, "male", 50, new Adress("Odesa"), studentGrades)
  )
  var вчителі: List[Teacher] = List( // лист вчителів
    new Teacher("Anna Vallidol", 35, "female", 100, new Adress("Kherson"), new Course("Програмування", 12)),
    new Teacher("Anton Alert", 41, "male", 100, new Adress("Kherson"), new Course("Групова динамiка", 6)),
    new Teacher("Genadiy Geron", 24, "male", 100, new Adress("Odesa"), new Course("Дискретна математика", 8))
  )
вчителі.foreach(teacher => {  // записування студентів на курси до вчителів
  val existingStudents = вчителі.filterNot(_ == teacher).flatMap(_.course.students)
  teacher.course.enrollStudents(random, existingStudents)
})

 def main(args: Array[String]): Unit = { // інтерактивне меню в консолі
  var showMenu = true
  while (showMenu) { // меню з вибором
    println("Меню:")
    println("1) Показати всiх людей")
    println("2) Показати оцiнки студентiв")
    println("3) Змiнити людей")
    println("4) Додати людину")
    println("5) Видалити людину")
    println("6) Вийти")

    val вибір = StdIn.readInt() // кейси
    вибір match {
      case 1 => показатиВсіхЛюдей()
      case 2 => показатиОцінки()
      case 3 => змінитиЛюдей()
      case 4 => додатиЛюдину()
      case 5 => видалитиЛюдину()
      case 6 => showMenu = false
      case _ => println("Невiрний вибiр.")
    }
  }
}

def показатиОцінки(): Unit = { // вивід курсів, та студентів з оцінками
  вчителі.foreach(teacher => { // ітераці по кожному вчителю в колекції вчителі
    println(s"Курс: ${teacher.course.courseName}, Викладач: ${teacher.fullName}") // вивід назви курсу та викладача який веде цей курс
    println("Студенти на курсi: ")
    teacher.course.students.foreach(student => { // ітерація по всім студентам в колекції студенти
      println(s"Iм'я: ${student.fullName}, Оцiнки: ${student.grades.grades.mkString(", ")}") // вивід їх даних
    })
    println()
  })
}

def показатиВсіхЛюдей(): Unit = { // вивід всіх людей
    println("Всi студенти: ")
    студенти.foreach(_.displayInfo())
    println("Всi вчителi: ")
    вчителі.foreach(_.displayInfo())
}

  def змінитиЛюдей(): Unit = { // зміна людей
    println("Введіть ім'я людини, яку бажаєте змінити:")
    val ім_я = StdIn.readLine()

    студенти.find(_.fullName == ім_я) match {
      case Some(студент) => студент.changeInfo()
      case None =>
        вчителі.find(_.fullName == ім_я) match {
          case Some(вчитель) => вчитель.changeInfo()
          case None => println(s"Людину з іменем $ім_я не знайдено.")
        }
    }
  }

  def додатиЛюдину(): Unit = { // додавання користувачів
    println("Оберіть тип людини:")
    println("1) Студент")
    println("2) Вчитель")
    val вибір = StdIn.readInt()
    вибір match {
      case 1 => додатиСтудента()
      case 2 => додатиВчителя()
      case _ => println("Невірний вибір.")
    }
  }

  def додатиСтудента(): Unit = { // додавання саме студента
    println("Введіть дані для нового студента:")
    val ім_я = StdIn.readLine()
    val вік = StdIn.readInt()
    val стать = StdIn.readLine()
    val монети = StdIn.readInt()
    val місто = StdIn.readLine()
    val новийСтудент = new Student(ім_я, вік, стать, монети, new Adress(місто), studentGrades)
    студенти = студенти :+ новийСтудент
    println(s"Студент $ім_я був доданий.")
  }
  def додатиВчителя(): Unit = { // додати саме вчителя
    println("Введіть дані для нового вчителя:")
    val ім_я = StdIn.readLine()
    val вік = StdIn.readInt()
    val стать = StdIn.readLine()
    val монети = StdIn.readInt()
    val місто = StdIn.readLine()
    val курс = StdIn.readLine()
    val новийВчитель = new Teacher(ім_я, вік, стать, монети, new Adress(місто), new Course(курс, 0))
    вчителі = вчителі :+ новийВчитель
    println(s"Вчитель $ім_я був доданий.")
  }

  def видалитиЛюдину(): Unit = { // видалити користувача за іменем
    println("Введіть ім'я людини, яку бажаєте видалити:")
    val ім_я = StdIn.readLine()

    студенти.find(_.fullName == ім_я) match {
      case Some(студент) =>
        студенти = студенти.filterNot(_ == студент)
        println(s"Студент $ім_я був видалений.")
      case None =>
        вчителі.find(_.fullName == ім_я) match {
          case Some(вчитель) =>
            вчителі = вчителі.filterNot(_ == вчитель)
            println(s"Вчитель $ім_я був видалений.")
          case None =>
            println(s"Людину з іменем $ім_я не знайдено.")
        }
    }
  }
}

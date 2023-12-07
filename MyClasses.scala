import scala.io.StdIn
import scala.util.Random
import Main.студенти

// додаткові класи

class Human(var fullName: String, var age: Int, var sex: String) // головний клас Human
class Adress(var city: String) // клас для адреси
class Grades(var grades: List[Int]) { // клас для оцінок
  def displayGrades(): Unit = {
    println("Оцінки: " + grades.mkString(", "))
  }
}

class Course(var courseName: String, var month: Int) { // клас для курсів вчителів
  var students: List[Student] = List() // список студентів
  def enrollStudents(random: Random, existingStudents: List[Student]): Unit = { // метод відповідає за запис студентів на курс
    val potentialStudents = студенти.filterNot(existingStudents.contains) // визначається список потенційних студентів, які ще не зареєстровані на інших курсах.
    students = potentialStudents.take(random.nextInt(potentialStudents.length) + 1)  // з потенційних студентів випадковим чином обирається певна кількість студентів
    students.foreach(student => { // ітерація по кожному студенту
      student.grades = new Grades(List.fill(month)(random.nextInt(11))) // генерація оцінок
    })
  }
}

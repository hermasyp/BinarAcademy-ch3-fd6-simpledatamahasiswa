package dao

import model.Classroom
import model.Student

class StudentDaoImpl : StudentDao {
    private var students = arrayListOf<Student>()

    init {
        students.apply {
            add(Student("Tony Stark", "12345", Classroom("4IA01","IT")))
            add(Student("Natasha Romanov", "12346", Classroom("4IA01","IT")))
            add(Student("Bruce Banner", "12347", Classroom("4IA01","IT")))
            add(Student("Thor Saepudin", "12348", Classroom("4IA01","IT")))
        }
    }

    override fun getStudents(): ArrayList<Student> = students

    override fun addStudent(student: Student) {
        students.add(student)
    }

    override fun deleteStudent(studentId: String) {
        students.remove(students.find {
            it.studentId == studentId
        })
    }
}
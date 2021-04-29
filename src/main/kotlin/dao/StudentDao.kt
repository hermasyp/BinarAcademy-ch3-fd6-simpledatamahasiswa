package dao

import model.Student

interface StudentDao {
    fun getStudents(): ArrayList<Student>
    fun addStudent(student: Student)
    fun deleteStudent(studentId: String)
}
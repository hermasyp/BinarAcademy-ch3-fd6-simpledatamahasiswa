import dao.StudentDao
import dao.StudentDaoImpl
import model.Classroom
import model.Student
import kotlin.system.exitProcess

class App {
    private lateinit var dao: StudentDao

    fun initialize() {
        dao = StudentDaoImpl()
    }

    fun startApp() {
        navigateToMenu()
    }

    private fun printHeader() {
        println(
            """
            =======================================================
            Aplikasi Data Mahasiswa
            =======================================================
            1. Cetak Data Mahasiswa
            2. Tambah Data Mahasiswa
            3. Hapus Data Mahasiswa
            4. Keluar
            =======================================================
            Masukkan Pilihan Anda (1,2,3,4) ?
            =======================================================
        """.trimIndent()
        )
    }

    private fun navigateToMenu() {
        printHeader()
        readLine()?.let {
            selectMenu(it)
        }
    }

    private fun askToMainMenu() {
        println(
            """
            =======================================================
            Kembali ke main Menu ? (Y/N)
            =======================================================
        """.trimIndent()
        )
        if (readLine().equals("Y", ignoreCase = true)) {
            navigateToMenu()
        } else {
            exitProcess(0)
        }
    }

    private fun selectMenu(menu: String) {
        when (menu) {
            "1" -> {
                printStudent(dao.getStudents())
            }
            "2" -> {
                inputNewStudent()
            }
            "3" -> {
                deleteStudent()
            }
            "4" -> {
                exitProcess(0)
            }
            else -> {
                println("Pilihan Tidak Ada")
            }
        }
        askToMainMenu()
    }

    private fun printStudent(students: ArrayList<Student>) {
        if (students.size > 0) {
            students.forEachIndexed { index, student ->
                println(
                    """
                    =======================================================
                    Mahasiswa No-${index + 1}
                    =======================================================
                    Nama    : ${student.name}
                    NIM     : ${student.studentId}
                    Kelas   : ${student.classroom.className}
                    Jurusan : ${student.classroom.major}
                    """.trimIndent()
                )
            }
        } else {
            println("=======================================================")
            println("Tidak Ada Data Mahasiswa")
            println("=======================================================")
        }
    }

    private fun inputNewStudent() {
        println("=======================================================")
        println("Nama Mahasiswa = ")
        val name = readLine()
        println("NIM Mahasiswa = ")
        val studentId = readLine()
        println("Kelas = ")
        val className = readLine()
        println("Jurusan = ")
        val major = readLine()
        dao.addStudent(Student(name, studentId, Classroom(className, major)))
        println("=======================================================")
        println("Input Mahasiswa Berhasil")
    }

    private fun deleteStudent() {
        println("=======================================================")
        println("Hapus Data dengan NIM  = ")
        readLine()?.let {
            dao.deleteStudent(it)
        }
        println("=======================================================")
        println("Hapus Data Mahasiswa Berhasil")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = App()
            app.initialize()
            app.startApp()
        }
    }
}
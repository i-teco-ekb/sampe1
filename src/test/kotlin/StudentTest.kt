import org.junit.jupiter.api.Test
import test.kotlin.dao.StudentDao
import test.kotlin.entities.Group
import test.kotlin.entities.Student

class StudentTest {

    @Test
    fun saveStudentTest() {
        val student = Student(
                name = "Vasya",
                group = Group(name = "group1"),
                club = null
        )

        val studentDao = StudentDao()

        studentDao.save(student)

    }
}
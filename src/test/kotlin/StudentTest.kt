import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.kotlin.dao.SessionFactoryUtil
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
        val id = student.id

        assertThat(id).isNotNull()

        val newStudent: Student? = studentDao.get(id!!)

        println("--------------------------------------\n newStudent = $newStudent student = $student")

        assertThat(newStudent).isNotNull
        assertThat(newStudent?.name).isEqualTo(student.name)
        assertThat(newStudent?.group).isEqualTo(student.group)
        assertThat(newStudent?.club).isNull()
    }
}
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import test.kotlin.dao.IStudentDao
import test.kotlin.dao.StudentDao
import test.kotlin.dao.StudentDaoSpring
import test.kotlin.dao.StudentDaoTransactional
import test.kotlin.entities.Group
import test.kotlin.entities.Student

class StudentTest {

    @ParameterizedTest
    @ValueSource(classes = [StudentDao::class, StudentDaoTransactional::class, StudentDaoSpring::class])
    fun saveTest(iStudent : Class<IStudentDao>) {
        val dao = iStudent.newInstance()
        val student = Student(
                name = "Vasya",
                group = Group(name = "group1"),
                clubs = null
        )

        dao.save(student)
        val id = student.id

        assertThat(id).isNotNull()

        val newStudent: Student? = dao.get(id!!)
        val clubs = newStudent?.clubs
        val nClubs = clubs?.size

        println("--------------------------------------\n newStudent = $newStudent student = $student")

        assertThat(newStudent).isNotNull
        assertThat(newStudent?.name).isEqualTo(student.name)
        assertThat(newStudent?.group).isEqualTo(student.group)
        assertThat(clubs).isEmpty()
    }
}
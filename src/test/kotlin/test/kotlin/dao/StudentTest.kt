package test.kotlin.dao

import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Session
import org.junit.jupiter.api.Test
import test.kotlin.entities.Group
import test.kotlin.entities.Student

class StudentTest {

    @Test
    fun saveStudentTransactionalTest() {
        val student = Student(
                name = "Petya",
                group = Group(name = "group2"),
                clubs = null
        )

        val studentDao = StudentDao()

        studentDao.save(student)
        val id = student.id

        assertThat(id).isNotNull()

        val newStudent: Student? = studentDao.get(id!!)
        val clubs = newStudent?.clubs
        val nClubs = clubs?.size

        println("--------------------------------------\n newStudent = $newStudent, student = $student")

        assertThat(newStudent).isNotNull
        assertThat(newStudent?.name).isEqualTo(student.name)
        assertThat(newStudent?.group).isEqualTo(student.group)
        assertThat(clubs).isEmpty()
    }

    @Test
    fun nestedTransactionTest() {
        val student = Student(
                name = "Petya",
                group = Group(name = "group2"),
                clubs = null
        )


        val studentDaoTransactional = StudentDao()
        SessionFactoryUtil.transaction { session: Session ->
            studentDaoTransactional.save(student)
        }

        val newStudent = SessionFactoryUtil.transaction { session: Session ->
            student.id?.let { studentDaoTransactional.get(it) }
        }

        val clubs = newStudent?.clubs

        assertThat(newStudent).isNotNull
        assertThat(newStudent?.name).isEqualTo(student.name)
        assertThat(newStudent?.group).isEqualTo(student.group)
        assertThat(clubs).isEmpty()
    }
}
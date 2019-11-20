package test.kotlin.dao

import org.assertj.core.api.Assertions
import org.hibernate.Session
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.kotlin.entities.Group
import test.kotlin.entities.Student

@SpringBootTest
class StudentSpringTest {

    @Autowired
    lateinit var studentDaoSpring: StudentDaoSpring

    @Test
    fun springTransactionTest() {
        val student = Student(
                name = "Petya",
                group = Group(name = "group2"),
                clubs = null
        )

        SessionFactoryUtil.transaction { session: Session ->
            studentDaoSpring.save(student)
        }

        val newStudent = SessionFactoryUtil.transaction { session: Session ->
            student.id?.let { studentDaoSpring.get(it) }
        }

        val clubs = newStudent?.clubs

        Assertions.assertThat(newStudent).isNotNull
        Assertions.assertThat(newStudent?.name).isEqualTo(student.name)
        Assertions.assertThat(newStudent?.group).isEqualTo(student.group)
        Assertions.assertThat(clubs).isEmpty()
    }
}
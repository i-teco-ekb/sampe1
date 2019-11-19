package test.kotlin.dao

import org.hibernate.Session
import test.kotlin.entities.Student

class StudentDao {

    fun save(student: Student) {
        SessionFactoryUtil { session: Session ->
            session.save(student)
            session.flush()
        }
    }

    fun get(id: Long): Student? = SessionFactoryUtil { session: Session ->
        session.load(Student::class.java, id) as Student
    }

}

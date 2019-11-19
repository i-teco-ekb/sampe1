package test.kotlin.dao

import org.hibernate.Session
import test.kotlin.entities.Student

class StudentDao : IStudentDao {

    override fun save(student: Student) {
        SessionFactoryUtil.transaction { session: Session ->
            session.save(student)
            session.flush()
        }
    }

    override fun get(id: Long): Student? = SessionFactoryUtil.transaction { session: Session ->
        session.load(Student::class.java, id) as Student
    }
}
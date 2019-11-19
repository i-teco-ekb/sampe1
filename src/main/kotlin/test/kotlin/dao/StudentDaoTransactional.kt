package test.kotlin.dao

import org.hibernate.Session
import test.kotlin.entities.Student

class StudentDaoTransactional : IStudentDao {
    private val dao = StudentDao()

    override fun save(student: Student) {
        SessionFactoryUtil.transaction { session: Session ->
            dao.save(student)
        }
    }

    override fun get(id: Long): Student? = SessionFactoryUtil.transaction { session: Session ->
        dao.get(id)
    }
}
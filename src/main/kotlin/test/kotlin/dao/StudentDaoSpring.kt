package test.kotlin.dao

import org.hibernate.Session
import org.springframework.stereotype.Repository
import test.kotlin.entities.Student
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Propagation


@Repository
@Transactional
open class StudentDaoSpring(private val sessionFactory: SessionFactory) : IStudentDao {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun save(student: Student) {
        val session: Session = this.sessionFactory.currentSession
        session.save(student)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun get(id: Long): Student? {
        val session: Session = this.sessionFactory.currentSession
        return session.load(Student::class.java, id) as Student
    }
}
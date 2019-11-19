package test.kotlin.dao

import org.hibernate.Session
import test.kotlin.entities.Student
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.springframework.boot.convert.ApplicationConversionService.configure
import org.hibernate.cfg.Configuration;

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

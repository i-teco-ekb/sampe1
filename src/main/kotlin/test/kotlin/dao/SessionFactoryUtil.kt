package test.kotlin.dao

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import test.kotlin.entities.Club
import test.kotlin.entities.Group
import test.kotlin.entities.Student

object SessionFactoryUtil {

    @JvmStatic
    val sessionFactory = try {
        val configuration = Configuration().configure().apply {
            addAnnotatedClass(Student::class.java)
            addAnnotatedClass(Group::class.java)
            addAnnotatedClass(Club::class.java)
        }
        val builder = StandardServiceRegistryBuilder().applySettings(configuration.properties)
        configuration.buildSessionFactory(builder.build())
    } catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }

    val session: Session
        get() = sessionFactory.currentSession

    operator fun <T> invoke(block: (Session) -> T): T = block(session)

    fun <T> transaction(block: (Session) -> T): T {
        val session = this.session
        val transaction = session.transaction
        if (transaction.isActive) return block(session)
        return try {
            transaction.begin()
            val result = block(session)
            transaction.commit()
            result
        } catch (e: Throwable) {
            transaction.rollback()
            throw e
        }
    }
}
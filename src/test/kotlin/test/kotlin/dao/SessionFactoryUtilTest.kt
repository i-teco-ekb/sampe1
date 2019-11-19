package test.kotlin.dao

import org.hibernate.Session
import org.junit.jupiter.api.Test

internal class SessionFactoryUtilTest {

    @Test
    fun transactionalTest() {
        SessionFactoryUtil { tx: Session ->
            println("Transation is complete ${tx.transaction.status}")
        }
    }
}
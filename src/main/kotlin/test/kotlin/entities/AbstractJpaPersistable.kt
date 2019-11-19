package test.kotlin.entities

import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    open var id: T? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != Hibernate.unproxy(other).javaClass) return false

        if (other !is AbstractJpaPersistable<*>) return false

        return this.id?.let { it == other.id } ?: false
    }

    override fun hashCode(): Int {
        return 31
    }
}
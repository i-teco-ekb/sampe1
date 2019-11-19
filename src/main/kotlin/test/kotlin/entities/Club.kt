package test.kotlin.entities

import org.hibernate.annotations.Cascade
import javax.persistence.*

@Entity
@Table(name = "Club")
class Club (

        override var id: Long? = null,

        var name: String? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var students: List<Student>? = null
) : AbstractJpaPersistable<Long>() {

        override fun toString(): String {
                return "Club $id $name"
        }
}

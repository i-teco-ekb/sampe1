package test.kotlin.entities

import org.hibernate.annotations.Cascade
import javax.persistence.*

@Entity
@Table(name = "Club")
class Club (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false, insertable = true, updatable = true)
        var id: Long? = null,

        var name: String? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var students: List<Student>? = null
) {
        override fun toString(): String {
                return "Club $id $name"
        }
}

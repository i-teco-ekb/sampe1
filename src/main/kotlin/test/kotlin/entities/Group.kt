package test.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "GroupTable")
class Group (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false, insertable = true, updatable = true)
        var id: Long? = null,
        var name: String? = null,

        @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var students: List<Student>? = null

) {
        override fun toString(): String {
                return "Group $id $name"
        }
}

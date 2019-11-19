package test.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "Student")
class Student(

        override var id: Long? = null,
        var name: String? = null,

        @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.EAGER)
        var group: Group? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var club: List<Club>? = null
) : AbstractJpaPersistable<Long>() {

        override fun toString(): String {
                return "Student $id $name"
        }
}
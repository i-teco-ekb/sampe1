package test.kotlin.entities

import javax.persistence.*

@Entity
data class Club (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long? = null,

        var name: String? = null,

        @ManyToMany
        var students: List<Student>? = null
)

package test.kotlin.entities

import javax.persistence.*

@Entity
data class Group (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long? = null,
        var name: String? = null,

        @OneToMany
        var students: List<Student>? = null

)

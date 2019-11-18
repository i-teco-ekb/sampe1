package test.kotlin.entities

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Entity
data class Student(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long? = null,
        var name: String? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        var group: Group? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        var club: Club? = null
)
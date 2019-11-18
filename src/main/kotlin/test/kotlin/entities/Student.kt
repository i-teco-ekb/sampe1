package test.kotlin.entities

import org.hibernate.annotations.GeneratorType
import test.kotlin.Constants.GENERATION_TYPE
import javax.persistence.*

@Entity
@Table(name = "Student")
data class Student(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false, insertable = true, updatable = true)
        var id: Long? = null,
        var name: String? = null,

        @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.EAGER)
        var group: Group? = null,

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var club: List<Club>? = null
)
package test.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "GroupTable")
data class Group (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false, insertable = true, updatable = true)
        var id: Long? = null,
        var name: String? = null,

        @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL], fetch = FetchType.LAZY)
        var students: List<Student>? = null

)

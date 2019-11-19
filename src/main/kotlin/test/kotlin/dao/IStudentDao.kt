package test.kotlin.dao

import test.kotlin.entities.Student

interface IStudentDao {

    fun save(student : Student)

    fun get(id : Long) : Student?
}
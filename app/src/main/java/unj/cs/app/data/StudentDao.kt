package unj.cs.app.data

import androidx.room.*

@Dao
interface StudentDao {
    @Query("Select * from Student")
    suspend fun getAll(): List<Student>

    @Query("Select * from Student where uid = :uid")
    suspend fun getStudentByUid(uid: Int): List<Student>

    @Query("Select * from Student where _id = :id")
    suspend fun getStudentById(id: Int): List<Student>

    @Query("Select * from Student where name = :name")
    suspend fun getStudentByName(name: String): List<Student>

    @Query("Select * from Student where uid = :uid or name = :name")
    suspend fun getStudentOr(uid: Int, name: String) : List<Student>

    @Insert
    suspend fun insert(vararg student: Student)
    @Update
    suspend fun update(student: Student)
    @Delete
    suspend fun delete(student: Student)
}
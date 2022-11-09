package unj.cs.app.data

import androidx.room.*

@Dao
interface StudentDao {
    @Query("Select * from Student")
    fun getAll(): List<Student>
    @Query("Select * from Student where uid = :uid")
    fun getStudentByUid(uid: Int)

    @Query("Select * from Student where name = :name")
    fun getStudentByName(name: String)

    @Query("Select * from Student where uid = :uid or name = :name")
    fun getStudentOr(uid: Int, name: String)

    @Insert
    fun insert(vararg student: Student)
    @Update
    fun update(student: Student)
    @Delete
    fun delete(student: Student)
}
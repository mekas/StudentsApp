package unj.cs.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val uid:String,
    val name:String
){
    @PrimaryKey(autoGenerate = true)
    var _id: Int = -1
}
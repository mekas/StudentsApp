package unj.cs.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Student(
    val uid:String,
    val name:String
)
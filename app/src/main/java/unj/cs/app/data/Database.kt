package unj.cs.app.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class Database: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: Database? = null


        fun getInstance(context: Context) {
            INSTANCE?: Room.databaseBuilder(context.applicationContext, Database::class.java, "student_database")
                .fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}
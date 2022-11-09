package unj.cs.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class StudentViewModel : ViewModel() {
    var _studentList = MutableLiveData<MutableList<Student>>(StudentList.list)
    val studentList: LiveData<MutableList<Student>> get() = _studentList

    fun setStudent(student: Student, position: Int){
        val list: MutableList<Student> = studentList.value!!
        list[position] = student
        _studentList.value = list
        //_studentList.value!![position] = student
    }

    fun addStudent(student: Student){
        val list: MutableList<Student> = studentList.value!!
        list.add(student)
        _studentList.value = list
    }

    fun getStudent(i: Int): Student{
        return studentList.value!![i]
    }
}
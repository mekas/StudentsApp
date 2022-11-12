package unj.cs.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.FieldPosition

class StudentViewModel() : ViewModel() {
    var _studentList = MutableLiveData<List<Student>>()
    val studentList: LiveData<List<Student>> get() = _studentList

    fun loadStudent(dao: StudentDao){
        viewModelScope.launch {
            _studentList.value = dao.getAll()
        }
    }

    fun setStudent(student: Student, dao: StudentDao){
        val list: List<Student> = studentList.value!!
        //list[position] = student
        viewModelScope.launch {
            dao.update(student)
            _studentList.value = dao.getAll()
        }
        _studentList.value = list
        //_studentList.value!![position] = student
    }

    fun addStudent(student: Student, dao: StudentDao){
        val list: List<Student> = studentList.value!!
        //TODO: repair later
        viewModelScope.launch {
            dao.insert(student)
            _studentList.value = dao.getAll()
        }
    }

    fun getStudent(i: Int): Student{

        return studentList.value!![i]
    }

}
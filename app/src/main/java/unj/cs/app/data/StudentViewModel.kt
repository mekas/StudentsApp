package unj.cs.app.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.FieldPosition

class StudentViewModel(context: Context) : ViewModel() {
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
        }
        loadStudent(dao)
        _studentList.value = list
        //_studentList.value!![position] = student
    }

    fun addStudent(student: Student, dao: StudentDao){
        viewModelScope.launch {
            dao.insert(student)
        }
        loadStudent(dao)
    }

    fun getStudentBy_Id(dao: StudentDao, _id: Int): Student?{
        var list: List<Student>? = null;
        viewModelScope.launch {
            list = dao.getStudentById(_id)
        }
        if(!list!!.isEmpty() && list!!.size == 1){
            return list!![0]
        }
        return null
    }

    fun getStudent(pos: Int): Student{
        return studentList.value!![pos]
    }

}
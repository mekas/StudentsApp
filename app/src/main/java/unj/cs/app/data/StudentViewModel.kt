package unj.cs.app.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    var studentList = MutableLiveData<MutableList<Student>>(StudentList.list)
}
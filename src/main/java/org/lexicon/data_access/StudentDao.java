package org.lexicon.data_access;

import org.lexicon.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    Student find(int id);
    Student save(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}

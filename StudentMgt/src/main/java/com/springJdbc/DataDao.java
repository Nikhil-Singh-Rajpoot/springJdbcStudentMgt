package com.springJdbc;

import java.util.List;

import com.entity.Student;

public interface DataDao {
	public int insert(Student student);
	public int delete(int studentId);
	public List<Student> getActivList(boolean isActicve);
	public List<Student> getList();
	public Student getFindStudent(int studentId);
	public Student getByNameStudent(String  studentName, String studentCity);
}

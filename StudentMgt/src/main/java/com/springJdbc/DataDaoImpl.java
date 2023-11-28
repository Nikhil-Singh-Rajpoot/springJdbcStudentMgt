package com.springJdbc;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.entity.Student;

public class DataDaoImpl implements DataDao {

	private  JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(Student student) {
		// INSERT DATA
		String insertQuery = "insert into student (stu_id,stu_name,stu_mobile_no,stu_city,is_active) values(?,?,?,?,?)";
		int inserted = jdbcTemplate.update(insertQuery, student.getStuId(),student.getStuName(),student.getStuMobileNo(),student.getStuCity(),student.isActive());
		return inserted;
	}

	@Override
	public int delete(int studentId) {
		// DELETE DATA
		String deleteQuery = "delete from student where stu_id=?";
		int deleted = jdbcTemplate.update(deleteQuery, studentId);
		return deleted;
	}

	@Override
	public List<Student> getActivList(boolean isActive) {
		// SELECT SPECFIC DATA
		String selectQuery = "select * from student where is_active=?";
		RowMapper< Student> rowMapper = new RowMapperImpl();
		List<Student> list = jdbcTemplate.query(selectQuery,rowMapper,isActive);
		return list;
	}

	@Override
	public List<Student> getList() {
		// SELECT ALL DATA 
		String selectString = "select * from student";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		List<Student> students = jdbcTemplate.query(selectString, rowMapper);
		return students;
	}

	@Override
	public Student getFindStudent(int studentId) {
		//FIND STUDENT BY STUDENT ID
		String selectQuery="select* from student where stu_id=?";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		Student student = jdbcTemplate.queryForObject(selectQuery, rowMapper,studentId);
		return student;
	}

	@Override
	public Student getByNameStudent(String studentName, String studentCity) {
		// FIND STUDENT BY NAME OR CITY
		String byNameQuery = "select * from student where stu_name=? and stu_city=?";
		RowMapper< Student> rowMapper = new RowMapperImpl();
		Student student = jdbcTemplate.queryForObject(byNameQuery, rowMapper,studentName,studentCity);
		return student;
	}

}

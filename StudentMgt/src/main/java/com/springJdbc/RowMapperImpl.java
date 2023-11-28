package com.springJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.entity.Student;

public class RowMapperImpl implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student st1 = new Student();
		st1.setStuId(rs.getInt(1));
		st1.setStuName(rs.getString(2));
		st1.setStuMobileNo(rs.getLong(3));
		st1.setStuCity(rs.getString(4));
		st1.setActive(rs.getBoolean(5));
		return st1;
	}

}

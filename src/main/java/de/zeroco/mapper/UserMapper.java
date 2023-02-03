package de.zeroco.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.zeroco.pojo.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("pk_id"));
		user.setUsername(rs.getString("user_name"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}

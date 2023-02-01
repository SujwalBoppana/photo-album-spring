package de.zeroco.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.zeroco.pojo.Photo;

public class PhotoMapper implements RowMapper<Photo>{

	public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Photo photo = new Photo();
		photo.setId(rs.getInt("pk_id"));
		photo.setImage(rs.getBytes("image"));
		return photo;
	}

}

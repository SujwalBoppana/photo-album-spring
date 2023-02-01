package de.zeroco.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import de.zeroco.mapper.PhotoMapper;
import de.zeroco.pojo.Photo;

public class PhotoDao {

	JdbcTemplate template;

	public PhotoDao(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	public int inserRecords(MultipartFile photo) throws IOException {
		byte[] photoBytes = photo.getBytes();
		String sql = "INSERT INTO photo(image) VALUES (?)";
		return template.update(sql, new Object[] { photoBytes });
	}
	public int update(int id ,byte[] photoBytes ) throws IOException{  
	    String sql="update photo set image= ? where pk_id= ?";  
	    return template.update(sql,new Object[] {photoBytes,id});  
	}  
	
	public Photo getPhotoById(int id) {
		String query = "select * from photo where pk_id=?";
		Photo photo = template.queryForObject(query, new Object[] { id }, new PhotoMapper());
 	   return photo;
	}
	
	public List<Photo> photoList() {
		List<Photo> list = template.query("SELECT * FROM photo", new RowMapper<Photo>() {
			public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Photo photo = new Photo();
				photo.setId(rs.getInt("pk_id"));
				photo.setImage(rs.getBytes("image"));
				return photo;
			}
		});
		return list;
	}
	
	public int delete(int id){  
	    String sql="delete from photo where pk_id="+id+"";  
	    return template.update(sql);  
	}  
	
	
}

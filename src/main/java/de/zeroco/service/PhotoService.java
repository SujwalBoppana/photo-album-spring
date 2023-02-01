package de.zeroco.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.zeroco.dao.PhotoDao;
import de.zeroco.pojo.Photo;

@Service
public class PhotoService {
	@Autowired
	PhotoDao dao;
	
	public  Photo encoder(Photo photo) {
		photo.setEncoder(Base64.getEncoder().encodeToString(photo.getImage()));
		return photo;
	}

	public int insert(MultipartFile[] filePart) {
		int id = 0;
		for (MultipartFile multipartFile : filePart) {
			try {
				if (multipartFile != null && multipartFile.getSize() < 10485760)
					id = dao.inserRecords(multipartFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public Photo getImgById(int id) {
		Photo photos = null;
		if (id > 0) {
			photos = dao.getPhotoById(id);
			encoder(photos);
		}
		return photos;
	}

	public List<Photo> photoList() {
		List<Photo> photos = dao.photoList();
		for (Photo photo : photos) {
			encoder(photo);
		}
		return photos;
	}

	public int update(int id ,MultipartFile photo) throws IOException{  
		byte[] photoBytes = photo.getBytes();
	    return dao.update(id , photoBytes);  
	}  
	public int delete(int id){  
		if (id>0) return dao.delete(id);  
		return 0;
		
	}  

	public static String encoder(byte[] image) {
		return Base64.getEncoder().encodeToString(image);
	}
}

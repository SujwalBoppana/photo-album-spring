package de.zeroco.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.zeroco.dao.PhotoDao;
import de.zeroco.pojo.Photo;
import de.zeroco.pojo.User;

@Service
public class PhotoService {
	@Autowired
	PhotoDao dao;

	public Photo encoder(Photo photo) {
		photo.setEncoder(Base64.getEncoder().encodeToString(photo.getImage()));
		return photo;
	}

	public String insert(MultipartFile[] filePart) {
		String message = "";
		byte[] photoBytes = null;
		for (MultipartFile multipartFile : filePart) {
			try {
				if ((!multipartFile.isEmpty() && multipartFile.getSize() < 10485760
						&& multipartFile.getContentType().startsWith("image/"))) {
					photoBytes = multipartFile.getBytes();
					message += multipartFile.getOriginalFilename() + " is  uploaded successfully" + " .";
					dao.inserRecords(photoBytes);
				} else {
					message += multipartFile.getOriginalFilename() + " is not allowed" + " .";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return message;
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

	public String update(int id, MultipartFile photo) throws IOException {
		if (!photo.isEmpty() && photo.getSize() < 10485760 && photo.getContentType().startsWith("image/")) {
			byte[] photoBytes = photo.getBytes();
			dao.update(id, photoBytes);
			return "data updated";
		} else {
			return photo.getOriginalFilename() + " is invalid format";
		}

	}

	public int delete(int id) {
		if (id > 0)
			return dao.delete(id);
		return 0;
	}

	public String encoder(byte[] image) {
		return Base64.getEncoder().encodeToString(image);
	}

	public List<User> validate(String userName, String pwd) {
		List<User> user = dao.validate(userName, pwd);
		return user;
	}
}

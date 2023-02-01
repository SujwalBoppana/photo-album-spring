package de.zeroco.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.zeroco.dao.PhotoDao;

@Service
public class PhotoService {
	@Autowired
	PhotoDao dao;

	public int insert(MultipartFile[] filePart) {
		int id = 0;
		for (MultipartFile multipartFile : filePart) {
			try {
				id= dao.inserRecords(multipartFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return id;

	}

	public static String encoder(byte[] image) {
		return Base64.getEncoder().encodeToString(image);
	}
}

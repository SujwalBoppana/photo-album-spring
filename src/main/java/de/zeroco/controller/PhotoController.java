package de.zeroco.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.zeroco.dao.PhotoDao;
import de.zeroco.pojo.Photo;
import de.zeroco.service.PhotoService;

@Controller
public class PhotoController {

	@Autowired
	PhotoDao dao;

	@Autowired
	PhotoService service;
	
	@RequestMapping("/InsertImage")
	public String save(@RequestParam("photos") MultipartFile[] filePart) throws IOException {
		service.insert(filePart);
		return "redirect:/viewAll";
	}

	@RequestMapping("/view")
	public String viewPage() {
		return "view";
	}
	
	@RequestMapping("/upload")
	public String upload() {
		return "upload";
	}

	@RequestMapping("/viewphoto")
	public String view(@RequestParam int pk_id, Model model) throws SQLException, ClassNotFoundException {
		Photo photos = dao.getPhotoById(pk_id);
		photos.setEncoder(Base64.getEncoder().encodeToString(photos.getImage()));
		model.addAttribute("image", photos.getEncoder());
		return "status";
	}

	@RequestMapping("/viewAll")
	public String list(Model model) throws IOException {
		List<Photo> photos = dao.photoList();
		for (Photo photo : photos) {
			photo.setEncoder(Base64.getEncoder().encodeToString(photo.getImage()));
		}
 		model.addAttribute("image", photos);
		return "ViewAll";
	}

	@RequestMapping(value = "/deleteimg/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewAll";
	}

	@RequestMapping(value = "/editimg/{id}")
	public String edit(@PathVariable int id, Model m) {
		Photo photos = dao.getPhotoById(id);
		photos.setEncoder(Base64.getEncoder().encodeToString(photos.getImage()));
		m.addAttribute("image", photos.getEncoder());
		m.addAttribute("pk_id", photos.getId());
		return "edit";
	}
	
    @RequestMapping(value="/editimg/updateImage",method = RequestMethod.POST)  
    public String editsave(@RequestParam("photo") MultipartFile filePart,@RequestParam("pk_id") int id) throws IOException{  
        dao.update(id,filePart);  
        return "redirect:/viewAll";  
    } 

}

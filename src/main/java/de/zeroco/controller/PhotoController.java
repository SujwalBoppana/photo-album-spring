package de.zeroco.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.zeroco.pojo.Photo;
import de.zeroco.pojo.User;
import de.zeroco.service.PhotoService;

@Controller
public class PhotoController {

	@Autowired
	PhotoService service;
	
	@RequestMapping(value = "/InsertImage",method = RequestMethod.POST)
	public String save(@RequestParam("photos") MultipartFile[] filePart, Model model) throws IOException {
		String message=service.insert(filePart);
		if(message.contains("not allowed")) {
			model.addAttribute("message", message);
			return "upload";
		}
		return "redirect:/viewAll";
	}

	@RequestMapping("/view")
	public String viewPage() {
		return "view";
	}
	
	@RequestMapping("/upload")
	public String uploadPage() {
		return "upload";
	}

	@RequestMapping("/viewphoto")
	public String view(@RequestParam("pk_id") int pk_id, Model model) throws SQLException, ClassNotFoundException {
		Photo photos = service.getImgById(pk_id);
		if (photos==null) 
			model.addAttribute("message", "no data found");
		else {
			model.addAttribute("photos", photos);
			model.addAttribute("message", "data found");
		}
		return "status";
	}

	@RequestMapping(value = "/viewAll",method = RequestMethod.GET)
	public String viewAll(Model model) throws IOException {
		List<Photo> photos = service.photoList();
 		model.addAttribute("image", photos);
		return "ViewAll";
	}

	@RequestMapping(value = "/deleteimg/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		service.delete(id);
		return "redirect:/viewAll";
	}

	@RequestMapping(value = "/editimg/{id}")
	public String get(@PathVariable int id, Model m, @ModelAttribute("message") String message) {
		Photo photos = service.getImgById(id);
		m.addAttribute("message", message);
		m.addAttribute("photos", photos);
		return "edit";
	}
	
    @RequestMapping(value="/editimg/updateImage",method = RequestMethod.POST)  
    public String edit(@RequestParam("photo") MultipartFile filePart,RedirectAttributes attributes,@RequestParam("pk_id") int id,Model model) throws IOException{  
        String message=service.update(id,filePart);  
        if (message.contains("invalid")) {
        	attributes.addFlashAttribute("message", message);
			return "redirect:/editimg/"+id;
		}
        return "redirect:/viewAll";  
    }
    
    @RequestMapping("/login")
	public String login(@RequestParam("username")String userName,@RequestParam("password")String pwd,Model model) {
    	List<User> users = service.validate(userName, pwd);
		if (users.size() > 0) {
			for (User user : users) {
				model.addAttribute("message","welcome user "	+user.getUsername());
			}
			return "sucess" ;
		}
    	else {
    		model.addAttribute("message","invaild user name and password");
    		return "login";
		}
	}

}

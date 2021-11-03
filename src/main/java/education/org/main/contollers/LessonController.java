package education.org.main.contollers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import education.org.main.entities.Lesson;
import education.org.main.services.LessonService;


@RestController
@RequestMapping("api/lessons/")
@CrossOrigin("localhost:4200")
public class LessonController {

	@Autowired
	private LessonService LessonService;

	@PostMapping("save")
	public  Lesson save(Lesson Lesson) {
		return LessonService.save(Lesson);
	}
	 
	@GetMapping("findById")
	public Lesson findById(Long id) {
		return LessonService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		LessonService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Lesson Lesson) {
		LessonService.delete(Lesson);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		LessonService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Lesson> findAll() {
		return LessonService.findAll();
	}
	
	



	
}

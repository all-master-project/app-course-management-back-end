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

import education.org.main.entities.Promotion;
import education.org.main.services.PromotionService;


@RestController
@RequestMapping("api/promotions/")
@CrossOrigin("localhost:4200")
public class PromotionController {

	@Autowired
	private PromotionService PromotionService;

	@PostMapping("save")
	public  Promotion save(Promotion Promotion) {
		return PromotionService.save(Promotion);
	}
	 
	@GetMapping("findById")
	public Promotion findById(Long id) {
		return PromotionService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		PromotionService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Promotion Promotion) {
		PromotionService.delete(Promotion);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		PromotionService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Promotion> findAll() {
		return PromotionService.findAll();
	}
	
}

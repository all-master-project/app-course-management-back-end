package education.org.main.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import education.org.main.dao.LessonRepository;
import education.org.main.entities.Lesson;

@Service
public class LessonService {

	private LessonRepository LessonRepo;
	public LessonService(LessonRepository LessonRepo) {
		this.LessonRepo = LessonRepo;
	}

	 
	public  Lesson save(Lesson Lesson) {
		return LessonRepo.save(Lesson);
	}
	 
	public Lesson findById(Long id) {
		return LessonRepo.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		LessonRepo.deleteById(id);
		
	}
	 
	public void delete(Lesson Lesson) {
		LessonRepo.delete(Lesson);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		LessonRepo.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		LessonRepo.deleteAll();			
	}
	 
	public List<Lesson> findAll() {
		
		return LessonRepo.findAll();
	}

	 
	public List<Lesson> findAll(Sort sort) {
		return LessonRepo.findAll();
	} 
}

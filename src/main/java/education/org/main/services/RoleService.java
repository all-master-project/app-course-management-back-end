package education.org.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import education.org.main.dao.RoleRepository;
import education.org.main.entities.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleService { 
	
	@Autowired
	private RoleRepository roleRepository;

	public  Role save(Role role) {
		return roleRepository.save(role);
	}

	public Role findById(Long id) {
		return roleRepository.findById(id).get();
	}

	public void deleteById(Long id) {

		roleRepository.deleteById(id);
	}

	public void delete(Role entity) {

		roleRepository.delete(entity);
	}

	public void deleteAll() {
		roleRepository.deleteAll();
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}

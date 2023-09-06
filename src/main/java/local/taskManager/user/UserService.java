package local.taskManager.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
//@Transactional
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public boolean registerUser(User u) {	
		String user = u.getEmail();		 
		if(user == null || repo.findById(user).isPresent())
			return false;
		String encryptedPass = bcrypt.encode(u.getPass());
		u.setPass(encryptedPass);
		addUser(u);
		return true;
	}
	
	//for updating existing and adding 
	// new after encrypting pass
	public User addUser(User u) {
		return repo.save(u);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Optional<User> getUserById(String id) {
		// TODO Auto-generated method stub		
		return repo.findById(id);
		
	}	

	public boolean login(User u) {
		// TODO Auto-generated method stub
		String user = u.getEmail();	
		if(user == null)
			return false;
		Optional<User> exist = repo.findById(user);
		if(exist.isEmpty()){
			return false;			
		}
		User encodedUser = exist.get();
		return bcrypt.matches(u.getPass(), encodedUser.getPass());		
	}


	public void deleteUserById(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}

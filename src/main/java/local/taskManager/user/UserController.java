package local.taskManager.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.taskManager.board.Board;
import local.taskManager.board.BoardService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private BoardService boardService;
	
        
	// Get user by ID
    @PostMapping("/login")
    public User login(@RequestBody User u) {
    	return userService.login(u);
    }	
	
    // Get all users
    @GetMapping("/all")    
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
 // Create a new user
    @PostMapping
    public boolean register(@RequestBody User u) {
        return userService.registerUser(u);     
    }   
    
    
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
    	userService.deleteUserById(id);        
    }
}

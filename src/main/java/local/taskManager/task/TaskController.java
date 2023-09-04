package local.taskManager.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import local.taskManager.myList.MyList;
import local.taskManager.myList.MyListService;


@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService ts;
	
	@Autowired
	private MyListService ls;
	
	// Create a new user
    @PostMapping
    public Task addTask(@RequestBody Task t) {
        return ts.addTask(t);
    }

    // Get all users
    @GetMapping()
    public List<Task> getAllTasks() {
        return ts.getAllTasks();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Optional<Task> getCountryById(@PathVariable Integer id) {
        return ts.getTaskById(id);
    }  
    @PutMapping    
    public Task updateTask(@RequestBody Task t) {
        return ts.updateTask(t);
    }   
    
    @PutMapping("/all")
    public List<Task> updateAllTasks(@RequestBody List<Task> tasks) {
        return ts.updateAllMyTask(tasks);
    }
    

    @PostMapping("/{id}/addTask")    
    public Task addTaskToMyListId(@RequestBody Task t, @PathVariable Integer id) {    	
    	MyList l = ls.getMyListById(id);    	
    	t.setParentList(l);
    	return ts.addTask(t);          
    }
    
    
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id) {
    	ts.deleteTaskById(id);        
    }
}

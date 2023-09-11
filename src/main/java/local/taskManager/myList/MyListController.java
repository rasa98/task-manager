package local.taskManager.myList;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import local.taskManager.board.Board;
import local.taskManager.task.Task;
import local.taskManager.task.TaskService;


@RestController
@RequestMapping("/lists")
public class MyListController {
	
	@Autowired
	private MyListService myListService;
	
	@Autowired
	private TaskService taskService;
	
	// Create a new user
    @PostMapping
    public MyList addMyList(@RequestBody MyList t) {
        return myListService.addMyList(t);
    }
    
    
    @PutMapping
    public MyList updateMyList(@RequestBody MyList l) {
        return myListService.updateMyList(l);
    }
    
    @PutMapping("/all")
    public List<MyList> updateAllMyList(@RequestBody List<MyList> updatedlists) {
            return myListService.updateAllMyList(updatedlists);
    }    
    

    // Get all users
    @GetMapping()
    public List<MyList> getAllMyLists() {
        return myListService.getAllMyLists();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public MyList getMyListById(@PathVariable Integer id) {
        return myListService.getMyListById(id);
    } 
    
    @GetMapping("/sorted/{id}")
    public MyList getMyListByIdSorted(@PathVariable Integer id) {
        return myListService.getMyListByIdSorted(id);
    }  
    
    @DeleteMapping("/{id}")
    public void deleteMyListById(@PathVariable Integer id) {
    	myListService.deleteMyListById(id);        
    }
}

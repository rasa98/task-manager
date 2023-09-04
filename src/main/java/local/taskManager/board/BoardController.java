package local.taskManager.board;

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

import local.taskManager.myList.MyList;
import local.taskManager.myList.MyListService;


@RestController
@RequestMapping("/boards")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MyListService myListService;
	
	// Create a new user
    @PostMapping
    public Board addBoard(@RequestBody Board t) {
        return boardService.addBoard(t);
    }
    
    @PostMapping("/{id}/addList")
    public MyList addTaskToBoard(@RequestBody MyList myList, @PathVariable Integer id) {
    	Board b = boardService.getBoardById(id);    	    	
		myList = myListService.addMyList(myList);
		b.getArrayOfLists().add(myList);  
		boardService.addBoard(b);
		return myList;    	      
    }

    // Get all users
    @GetMapping()
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Integer id) {
    	return boardService.getBoardById(id);    	
    }
    
 // Get user by ID sorted
    @GetMapping("/sorted/{id}")
    public Board getBoardByIdSorted(@PathVariable Integer id) {
        return boardService.getBoardByIdSorted(id);
    }  
    
    @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable Integer id) {
    	boardService.deleteBoardById(id);        
    }
}
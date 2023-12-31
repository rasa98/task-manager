package local.taskManager.board;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import local.taskManager.board.snapshot.BoardSnapshot;
import local.taskManager.board.snapshot.BoardSnapshotRepository;
import local.taskManager.myList.MyList;
import local.taskManager.task.Task;


@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardRepo repo;
	
	@Autowired
    private BoardSnapshotRepository boardSnapshotRepository;
	
	public Board addBoard(Board t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	public List<Board> getAllBoards() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Board getBoardById(Integer id) {
		// TODO Auto-generated method stub		
		Optional<Board> ob = repo.findById(id);
    	if(ob.isPresent())
    		return ob.get();
    	throw new RuntimeException("Nije nadjena tabla sa tim id-om!!!");
		
	}
	
	public Board getBoardByIdSortedOG(Integer id) {
		// TODO Auto-generated method stub
		Board b = getBoardById(id);
		
		List<MyList> listOfObjs = b.getArrayOfLists();
		if(listOfObjs != null) {
	    
	    // Sort the ListOfObj objects by listOrdering using Java streams
	    listOfObjs = listOfObjs.stream()
	        .sorted(Comparator.comparingInt(MyList::getListOrder))
	        .collect(Collectors.toList());
		}
		b.setArrayOfLists(listOfObjs);
		
		return b;
	}
	
	public Board getBoardByIdSorted(Integer id) {
	    // TODO: Retrieve the Board entity by id (assuming you have a method for this)
	    Board b = getBoardById(id);
	    
	    List<MyList> listOfObjs = b.getArrayOfLists();
	    
	    if (listOfObjs != null) {
	        // Sort the ListOfObj objects and their Task objects in a single pass
	        listOfObjs.forEach(myList -> {
	            myList.setTaskList(myList.getTaskList().stream()
	                .sorted(Comparator.comparingInt(Task::getTaskOrder))
	                .collect(Collectors.toList()));
	        });

	        listOfObjs = listOfObjs.stream()
	            .sorted(Comparator.comparingInt(MyList::getListOrder))
	            .collect(Collectors.toList());
	    }
	    
	    b.setArrayOfLists(listOfObjs);
	    
	    return b;
	}

	public void deleteBoardById(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public Board updateNameBoard(Board b) {
		Optional<Board> ob = repo.findById(b.getId());
		if(ob.isPresent()) {
			Board exist = ob.get();
			exist.setName(b.getName());
			return repo.save(exist);
		}
		return null;
	}
	
	public BoardSnapshot createSnapshot(Integer boardId) {
	    Board board = repo.findById(boardId).orElse(null);

	    if (board == null) {
	        return null;
	    }
	 
        long totalTasks = board.getArrayOfLists().stream()
                .flatMap(list -> list.getTaskList().stream()).count();
        long completedTasks = board.getArrayOfLists().stream()
                .flatMap(list -> list.getTaskList().stream())
                .filter(Task::getDone).count();
        long undoneTasks = totalTasks - completedTasks;

        BoardSnapshot snapshot = new BoardSnapshot();
        snapshot.setSnapshotDate(LocalDateTime.now());
        snapshot.setTotalTasks((int) totalTasks);
        snapshot.setCompletedTasks((int) completedTasks);
        snapshot.setUndoneTasks((int) undoneTasks);
        
        snapshot.setBoard(board);

        // Save the snapshot
        return boardSnapshotRepository.save(snapshot);
	}
    
    public List<BoardSnapshot> getSnapshotsForBoard(Integer boardId) {
        // Retrieve snapshots for the specified board
        return boardSnapshotRepository.findByBoardId(boardId);
    }
}

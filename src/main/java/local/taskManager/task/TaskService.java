package local.taskManager.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import local.taskManager.myList.MyList;
import local.taskManager.myList.MyListService;


@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepo tr;
	
	@Autowired
	private MyListService ls;
	
	public Task addTask(Task t) {
		// TODO Auto-generated method stub
		if(t.getDone() == null)
			t.setDone(false);
		return tr.save(t);
	}
	
	public List<Task> addAllTask(List<Task> tasks) {
		// TODO Auto-generated method stub
		return tr.saveAll(tasks);
	}

	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return tr.findAll();
	}

	public Optional<Task> getTaskById(Integer id) {
		// TODO Auto-generated method stub
		return tr.findById(id);
	}
	
	// All must be found
	public List<Task> getAllTaskByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Task> entities = tr.findAllById(ids);
		
		if(ids.size() != entities.size())
			throw new RuntimeException("Neki Taskovi za zadate id ne postoje!!!");
	    
	    // Create a map of IDs to entities for efficient lookup
	    Map<Integer, Task> entityMap = new HashMap<>();
	    for (Task entity : entities) {
	        entityMap.put(entity.getId(), entity);
	    }

	    // Create a list of entities in the order of IDs
	    List<Task> orderedEntities = new ArrayList<>();
	    for (Integer id : ids) {
	        orderedEntities.add(entityMap.get(id));
	    }
	    
	    return orderedEntities;		
	}

	public void deleteTaskById(Integer id) {
		// TODO Auto-generated method stub
		tr.deleteById(id);
	}

	public Task updateTask(Task updatedTask) {
		// TODO Auto-generated method stub
		Task existingTask = tr.findById(updatedTask.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                		"Task with id: "+updatedTask.getId()+" not found"));

        // 2. Modify the entity with new values
		applyChangesOnTask(updatedTask, existingTask);

        // 3. Save the updated entity
		return tr.save(existingTask);
	}
	
	// only change tose that are sent, so check for default values
	private void applyChangesOnTask(Task updatedTask, Task existingTask) {
		if(updatedTask.getTitle() != null)
			existingTask.setTitle(updatedTask.getTitle());
		if(updatedTask.getTaskOrder() != -1)
			existingTask.setTaskOrder(updatedTask.getTaskOrder());
		if(updatedTask.getDone() != null)
			existingTask.setDone(updatedTask.getDone());
		if(updatedTask.getParentList() != null) {
			MyList myList = updatedTask.getParentList();
			myList = ls.getMyListById(myList.getId()); // todo  treba napomena ovde...			
			existingTask.setParentList(myList);
		}
	}
	public List<Task> updateAllMyTask(List<Task> listOfUpdatedTask) {
		// TODO Auto-generated method stub
		List<Integer> listOfIds = listOfUpdatedTask.stream()
                .map(t -> t.getId()) // Extract the value associated with the key "key"
                .collect(Collectors.toList());
        
        List<Task> forUpdateTasks = getAllTaskByIds(listOfIds);
        
        // mutate forUpdateMyLists
        for (int i = 0; i < listOfUpdatedTask.size(); i++) {
        	Task t1 = listOfUpdatedTask.get(i);
        	Task t2 = forUpdateTasks.get(i);
        	
        	applyChangesOnTask(t1, t2);
        }     
        
        
        return tr.saveAll(forUpdateTasks);
	}
}

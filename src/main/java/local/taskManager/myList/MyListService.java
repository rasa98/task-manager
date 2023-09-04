package local.taskManager.myList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import local.taskManager.task.Task;


@Service
@Transactional
public class MyListService {
	
	@Autowired
	private MyListRepo repo;
	
	public MyList addMyList(MyList t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}
	
	public List<MyList> addAllMyList(List<MyList> lists) {
		// TODO Auto-generated method stub
		return repo.saveAll(lists);
	}

	public List<MyList> getAllMyLists() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public MyList getMyListById(Integer id) {
		Optional<MyList> ol = repo.findById(id);
		if(ol.isPresent()) {
			return ol.get();
		} 
		throw new RuntimeException("Nije nadjena lista sa sledecim id-om: "+id);
	}
	public MyList getMyListByIdSorted(Integer id) {
		// TODO Auto-generated method stub
		MyList l = getMyListById(id);
		
		List<Task> listOfTasks = l.getTaskList();
		if(listOfTasks != null) {
	    
	    // Sort the ListOfObj objects by listOrdering using Java streams
			listOfTasks = listOfTasks.stream()
	        .sorted(Comparator.comparingInt(Task::getTaskOrder))
	        .collect(Collectors.toList());
		}
		l.setTaskList(listOfTasks);
		
		return l;
	}
	
	public List<MyList> getAllMyListByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<MyList> entities = repo.findAllById(ids);
		
		if(ids.size() != entities.size())
			throw new RuntimeException("Neke liste za zadate id ne postoje!!!");
	    
	    // Create a map of IDs to entities for efficient lookup
	    Map<Integer, MyList> entityMap = new HashMap<>();
	    for (MyList entity : entities) {
	        entityMap.put(entity.getId(), entity);
	    }

	    // Create a list of entities in the order of IDs
	    List<MyList> orderedEntities = new ArrayList<>();
	    for (Integer id : ids) {
	        orderedEntities.add(entityMap.get(id));
	    }
	    
	    return orderedEntities;
	}

	public void deleteTaskById(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public MyList updateMyList(MyList updatedMyList) {
		// TODO Auto-generated method stub
		MyList existingMyList = repo.findById(updatedMyList.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                		"MyList with id: "+updatedMyList.getId()+" not found"));

        updateForChanges(updatedMyList, existingMyList);
		

        // 3. Save the updated entity
		return repo.save(existingMyList);

	}

	private void updateForChanges(MyList updatedMyList, MyList existingMyList) {
		// 2. Modify the entity with new values
		if(updatedMyList.getName() != null)
			existingMyList.setName(updatedMyList.getName());
		if(updatedMyList.getListOrder() != -1)
			existingMyList.setListOrder(updatedMyList.getListOrder());
		if(updatedMyList.getTaskList() != null)
			existingMyList.setTaskList(updatedMyList.getTaskList());
	}
	
	public List<MyList> updateAllMyList(List<MyList> listOfUpdatedMyList) {
		// TODO Auto-generated method stub
		List<Integer> listOfIds = listOfUpdatedMyList.stream()
                .map(l -> l.getId()) // Extract the value associated with the key "key"
                .collect(Collectors.toList());
        
        List<MyList> forUpdateMyLists = getAllMyListByIds(listOfIds);
        
        // mutate forUpdateMyLists
        for (int i = 0; i < listOfUpdatedMyList.size(); i++) {
        	MyList l1 = listOfUpdatedMyList.get(i);
        	MyList l2 = forUpdateMyLists.get(i);
        	
        	updateForChanges(l1, l2);
        }
        
        repo.saveAll(forUpdateMyLists);
        
        return forUpdateMyLists;
	}
	
}

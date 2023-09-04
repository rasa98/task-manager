package local.taskManager.myList;

import local.taskManager.task.Task;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MyList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int listOrder;
	
	@OneToMany(mappedBy = "parentList", cascade = CascadeType.ALL)
	private List<Task> taskList;
	
	public MyList(int id, String name, int listOrder, List<Task> taskList) {
		super();
		this.id = id;
		this.name = name;
		this.listOrder = listOrder;
		this.taskList = taskList;
	}

	public MyList() {
		super();
		this.listOrder = -1;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}	
	
}

package local.taskManager.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import local.taskManager.myList.MyList;
import local.taskManager.util.CustomTaskDeserializer;

@Entity
@JsonDeserialize(using = CustomTaskDeserializer.class)
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private int taskOrder;
	
	@Column(name = "is_done")
    private Boolean done = false;
	
	@ManyToOne	
	private MyList parentList;
	
	public Task() {
		super();
		this.taskOrder = -1;		
	}
	
	
	public Task(int id, String title, int taskOrder) {
		super();
		this.id = id;
		this.title = title;
		this.taskOrder = taskOrder;
	}
	
	@JsonProperty("parentList")
	public int getParentId() {
		return (parentList != null) ? parentList.getId() : null;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTaskOrder() {
		return taskOrder;
	}

	public void setTaskOrder(int taskOrder) {
		this.taskOrder = taskOrder;
	}	
	

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}


	public MyList getParentList() {
		return parentList;
	}


	public void setParentList(MyList parentList) {
		this.parentList = parentList;
	}
	
}

package local.taskManager.board;

import local.taskManager.myList.MyList;
import local.taskManager.user.User;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;	
	
	
	@OneToMany
	@JoinColumn(name = "lists")
	private List<MyList> arrayOfLists;
	
	@ManyToOne
	@JsonIgnore
	private User parentUser;
	
	public Board() {
		super();
	}	
	
	public Board(int id, String name, List<MyList> arrayOfLists) {
		super();
		this.id = id;
		this.name = name;
		this.arrayOfLists = arrayOfLists;
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

	public List<MyList> getArrayOfLists() {
		return arrayOfLists;
	}

	public void setArrayOfLists(List<MyList> arrayOfLists) {
		this.arrayOfLists = arrayOfLists;
	}

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}
	
	
}

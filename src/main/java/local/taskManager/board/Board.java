package local.taskManager.board;

import local.taskManager.board.snapshot.BoardSnapshot;
import local.taskManager.myList.MyList;
import local.taskManager.user.User;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lists")
	private List<MyList> arrayOfLists;
	
	@ManyToOne
	@JsonIgnore
	private User parentUser;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
    private List<BoardSnapshot> snapshots = new ArrayList<>();
	
	public Board() {
		super();
	}	
	
	public Board(int id, String name, List<MyList> arrayOfLists) {
		super();
		this.id = id;
		this.name = name;
		this.arrayOfLists = arrayOfLists;
	}
	
	public void addSnapshot(BoardSnapshot snapshot) {
        snapshots.add(snapshot);
        snapshot.setBoard(this);
    }	

	public List<BoardSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<BoardSnapshot> snapshots) {
		this.snapshots = snapshots;
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

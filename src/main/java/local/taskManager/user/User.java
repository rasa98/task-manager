package local.taskManager.user;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import local.taskManager.board.Board;


@Entity
@Table(name = "MyUser")
public class User {
	
	@Id
	private String email;	
	private String pass;	
	
	
	@OneToMany(mappedBy = "parentUser", cascade = CascadeType.ALL)	
	private List<Board> boards;
	
	public User() {
		super();
	}	
	
	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Board> getBoards() {
		return boards;
	}


	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
			
	
}

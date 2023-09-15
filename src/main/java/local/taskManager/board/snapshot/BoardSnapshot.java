package local.taskManager.board.snapshot;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import local.taskManager.board.Board;

@Entity
public class BoardSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime snapshotDate;

    @ManyToOne
    @JoinColumn(name = "board_id")
//    @JsonIgnore
    private Board board;

    private int totalTasks;
    private int completedTasks;
    private int undoneTasks;
    
	public BoardSnapshot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(LocalDateTime snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	public int getUndoneTasks() {
		return undoneTasks;
	}

	public void setUndoneTasks(int undoneTasks) {
		this.undoneTasks = undoneTasks;
	}
   
}

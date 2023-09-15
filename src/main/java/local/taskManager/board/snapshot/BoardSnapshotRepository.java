package local.taskManager.board.snapshot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardSnapshotRepository extends JpaRepository<BoardSnapshot, Integer> {

	List<BoardSnapshot> findByBoardId(Integer boardId);
    
}
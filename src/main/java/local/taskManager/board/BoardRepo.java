package local.taskManager.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepo extends JpaRepository<Board, Integer> {	
}

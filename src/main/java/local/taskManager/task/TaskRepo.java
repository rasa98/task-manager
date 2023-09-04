package local.taskManager.task;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Task, Integer> {}

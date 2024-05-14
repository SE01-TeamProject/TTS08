package DB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface repo_comment extends JpaRepository<DB_comment, Long> {
}

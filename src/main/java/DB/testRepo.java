package DB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface testRepo extends JpaRepository<testEntity, Long> {
    public testEntity findByName(String name);
}

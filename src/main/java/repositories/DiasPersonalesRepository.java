package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.DiasPersonales;

@Repository
public interface DiasPersonalesRepository extends JpaRepository<DiasPersonales, Integer>{

}

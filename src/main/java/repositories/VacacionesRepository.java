package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Vacaciones;

@Repository
public interface VacacionesRepository extends JpaRepository<Vacaciones, Integer> {

}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Reservas;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {

}

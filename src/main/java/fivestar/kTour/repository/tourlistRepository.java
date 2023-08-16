package fivestar.kTour.repository;

import fivestar.kTour.domain.Tourlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tourlistRepository extends JpaRepository<Tourlist,Long> {
}

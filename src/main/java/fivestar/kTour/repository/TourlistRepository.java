package fivestar.kTour.repository;

import fivestar.kTour.domain.Tourlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourlistRepository extends JpaRepository<Tourlist,Long> {
}

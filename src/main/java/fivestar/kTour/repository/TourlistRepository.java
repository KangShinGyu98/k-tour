package fivestar.kTour.repository;

import fivestar.kTour.domain.Tourlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourlistRepository extends JpaRepository<Tourlist,Long> {
    List<Tourlist> findAllByUserEmail(String userEmail);
    List<Tourlist> findAllByUserEmailNot(String userEmail);
}

package fivestar.kTour.repository;

import fivestar.kTour.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
    List<Place> findAllByPlan_PlanId(Long planId);
}

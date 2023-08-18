package fivestar.kTour.repository;

import fivestar.kTour.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findAllByUser_UserEmail(String userEmail);
    @Modifying
    @Query("UPDATE Plan p SET p.passed = :passedValue WHERE p.planId = :planId")
    int updatePassedStatus(@Param("planId") Long planId, @Param("passedValue") boolean passedValue);
}

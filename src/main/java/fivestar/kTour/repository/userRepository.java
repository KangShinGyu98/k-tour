package fivestar.kTour.repository;

import fivestar.kTour.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,String> {
}

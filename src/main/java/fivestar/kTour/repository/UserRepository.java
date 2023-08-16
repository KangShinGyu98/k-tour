package fivestar.kTour.repository;

import fivestar.kTour.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}

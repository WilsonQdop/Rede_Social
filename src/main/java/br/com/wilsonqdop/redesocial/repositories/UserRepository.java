package br.com.wilsonqdop.redesocial.repositories;

import br.com.wilsonqdop.redesocial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

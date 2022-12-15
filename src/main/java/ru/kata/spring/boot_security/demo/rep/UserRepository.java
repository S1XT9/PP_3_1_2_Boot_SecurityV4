package ru.kata.spring.boot_security.demo.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //унаследовав JpaRepository можно выполнять стандартные запросы к БД.

    // Если понадобиться специфичный метод просто добавляем его в интерфейс
    public User findUserByUsername(String username);

}

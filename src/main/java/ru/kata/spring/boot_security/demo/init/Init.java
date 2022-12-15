package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.rep.UserRepository;

@Component
public class Init implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public Init(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUsername("Admin");
        admin.setPassword("$2a$12$raaPrNYaXZyR2aCT28LJa.WCOGqPBwOCkeeCk5BzbiJ0nkh1kgtWi"); //admin
        admin.setFirstname("Ark");
        admin.setLastname("Kud");
        admin.setEmail("admin@email.com");
        admin.setAge(30);
        admin.setRoleSet(new String[]{"ROLE_ADMIN"});
        userRepository.save(admin);

        User user = new User();
        user.setUsername("User");
        user.setPassword("$2a$12$5j0eeeh55oVpp2tB3a0IgeqsYoYZ3dnCilhmRQ7c9wUzpHD8x.trO"); //user
        user.setFirstname("Lena");
        user.setLastname("Vas");
        user.setEmail("user@email.com");
        user.setAge(22);
        user.setRoleSet(new String[]{"ROLE_USER"});
        userRepository.save(user);

    }
}

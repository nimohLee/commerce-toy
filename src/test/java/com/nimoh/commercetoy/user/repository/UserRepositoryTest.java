package com.nimoh.commercetoy.user.repository;

import com.nimoh.commercetoy.user.domain.User;
import com.nimoh.commercetoy.user.dto.UserUpdateRequestDto;
import com.nimoh.commercetoy.user.enums.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Import(UserRepository.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;
    @Test
    void saveAndFindById() {
        User user = User.builder()
                .name("nimoh")
                .build();

        User savedUser = userRepository.save(user);

        User findUser = userRepository.findById(savedUser.getId()).get();

        assertThat(findUser.getName()).isEqualTo(user.getName());
    }

    @Test
    void save_duplicate_exception() {
        User user1 = User.builder()
                .email("asdf@naver.com")
                .loginId("test")
                .password("password")
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .email("asdf@naver.com")
                .password("password")
                .loginId("test123")
                .build();

        User user3 = User.builder()
                .email("asdf2@naver.com")
                .password("password")
                .loginId("test")
                .build();

        assertThatThrownBy(() -> userRepository.save(user2)).isInstanceOf(ConstraintViolationException.class);
        assertThatThrownBy(() -> userRepository.save(user3)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name("nimoh" + i)
                    .build();
            userRepository.save(user);
        }

        List<User> findUserList = userRepository.findAll();
        assertThat(findUserList.get(0).getName()).isEqualTo("nimoh0");
        assertThat(findUserList.get(9).getName()).isEqualTo("nimoh9");
        assertThat(findUserList.size()).isEqualTo(10);
    }

    @Test
    void update() {
        User user = User.builder()
                .name("nimoh")
                .phone("010-0000-0000")
                .birth("100322")
                .loginId("nimoh123")
                .password("test1234")
                .email("test123@naver.com")
                .gender(Gender.MALE)
                .build();

        User savedUser = userRepository.save(user);
        em.flush();
        em.clear();

        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setName("modi");
        dto.setPhone("010-1111-1111");
        dto.setLoginId("modi123");
        dto.setPassword("modi1234");
        dto.setEmail("modi123@naver.com");

        userRepository.update(savedUser.getId(), dto);

        User findUser = userRepository.findById(savedUser.getId()).get();

        assertThat(findUser.getName()).isEqualTo("modi");
        assertThat(findUser.getPhone()).isEqualTo("010-1111-1111");
        assertThat(findUser.getLoginId()).isEqualTo("modi123");
        assertThat(findUser.getPassword()).isEqualTo("modi1234");
        assertThat(findUser.getEmail()).isEqualTo("modi123@naver.com");
    }

    @Test
    void delete() {
        User user = User.builder()
                .name("nimoh" )
                .build();
        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser.getId());

        assertThatThrownBy(() -> userRepository.findById(savedUser.getId())).isInstanceOf(NullPointerException.class);

    }

}
package com.nimoh.commercetoy.user.repository;

import com.nimoh.commercetoy.user.domain.User;
import com.nimoh.commercetoy.user.dto.UserUpdateRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        User findUser = em.find(User.class, id);
        return Optional.of(findUser);
    }

    public List<User> findAll() {
        String sql = "select u from User u";
        return em.createQuery(sql, User.class).getResultList();
    }

    public void update(long userId, UserUpdateRequestDto dto) {
        User findUser = em.find(User.class, userId);

        findUser.setName(dto.getName());
        findUser.setEmail(dto.getEmail());
        findUser.setPassword(dto.getPassword());
        findUser.setPhone(dto.getPhone());
        findUser.setLoginId(dto.getLoginId());

    }

    public void delete(long userId) {
        User findUser = em.find(User.class, userId);

        em.remove(findUser);
    }
}

package com.example.mysql;

import org.springframework.data.repository.CrudRepository;

// Spring에 의해 userRepository라는 Bean으로 자동 구현
public interface UserRepository extends CrudRepository<User, Integer> {
}

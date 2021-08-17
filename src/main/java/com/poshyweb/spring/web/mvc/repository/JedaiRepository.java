package com.poshyweb.spring.web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poshyweb.spring.web.mvc.nodel.Jedai;
@Repository
public interface JedaiRepository  extends JpaRepository<Jedai, Long>{


}

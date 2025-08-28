package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Metas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetasRepository extends JpaRepository<Metas, Integer> {
}
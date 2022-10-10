package com.luiz.mendes.activities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luiz.mendes.activities.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}

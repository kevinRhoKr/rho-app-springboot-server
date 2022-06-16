package com.example.demo.top;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RequestRepository extends JpaRepository<Requst, Integer> {

}
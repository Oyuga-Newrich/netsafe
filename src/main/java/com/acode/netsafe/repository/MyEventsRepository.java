package com.acode.netsafe.repository;

import com.acode.netsafe.entity.MyEventList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyEventsRepository extends JpaRepository<MyEventList, Integer> {
}

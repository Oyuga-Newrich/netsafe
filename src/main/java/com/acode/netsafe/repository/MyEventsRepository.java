package com.acode.netsafe.repository;

import com.acode.netsafe.entity.MyEventList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyEventsRepository extends JpaRepository<MyEventList, Integer> {
    @Query(value = "SELECT * FROM my_events WHERE "
            + "MATCH(details, major_category, minor_category, town, location) "
            + "AGANIST (?1)",
                nativeQuery = true)
    public List<MyEventList> search(String keyword);
}

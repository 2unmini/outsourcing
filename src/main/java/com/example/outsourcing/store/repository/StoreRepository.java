package com.example.outsourcing.store.repository;

import com.example.outsourcing.store.dto.StoreInfoResponseDto;
import com.example.outsourcing.store.entity.State;
import com.example.outsourcing.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByStoreNameContainsAndState(String text, State state);


    Optional<Store> findByIdAndState(Long id, State state);

    default Store findByAndStateOrElseThrow(Long id,State state) {
        return findByIdAndState(id,state).orElseThrow();
    }


}

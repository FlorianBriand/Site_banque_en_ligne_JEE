package com.example.live.dao.compteCourant;

import com.example.live.model.compteCourant.Comptecourant;
import com.example.live.model.compteCourant.Operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    @Query("select o from Operation o where o.comptecourantIdcomptecourant = ?1")
    List<Operation> findOperationsByComptecourantIdcomptecourant(Comptecourant comptecourant);

    @Query("SELECT sum(o.montant) FROM Operation o ")
    int totalMontant(@Param("id") int id);


    //Operation findById(int id);


}
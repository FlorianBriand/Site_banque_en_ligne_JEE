package com.example.live.dao.historique;

import com.example.live.model.historique.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {

    @Query("select h from Historique h where h.idutilisateur = ?1")
    List<Historique> findAllByidutilisateur(int i);

    //@Query("SELECT h from Historique h where h.idutilisateur=")
    //Historique findHistoriqueByDateMax();

}

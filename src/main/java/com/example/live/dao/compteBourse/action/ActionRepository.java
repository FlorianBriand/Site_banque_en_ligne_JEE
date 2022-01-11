package com.example.live.dao.compteBourse.action;

import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteCourant.Comptecourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {

    Action findActionById(int i);

    Action findActionBySymbol(String symbol);

}
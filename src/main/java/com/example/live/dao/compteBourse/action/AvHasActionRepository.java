package com.example.live.dao.compteBourse.action;

import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasAction;
import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasActionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvHasActionRepository extends JpaRepository<AssurancevieHasAction, AssurancevieHasActionId> {

    AssurancevieHasAction findAssurancevieHasActionById(AssurancevieHasActionId AssurancevieHasActionId);

    boolean existsAssurancevieHasActionById(AssurancevieHasActionId AssurancevieHasActionId);
}

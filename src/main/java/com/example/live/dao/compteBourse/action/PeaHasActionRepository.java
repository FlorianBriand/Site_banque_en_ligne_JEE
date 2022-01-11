package com.example.live.dao.compteBourse.action;

import com.example.live.model.compteBourse.pea.PeaHasAction;
import com.example.live.model.compteBourse.pea.PeaHasActionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PeaHasActionRepository extends JpaRepository<PeaHasAction, PeaHasActionId> {

    PeaHasAction findPeaHasActionById(PeaHasActionId peaHasActionId);

    boolean existsPeaHasActionById(PeaHasActionId peaHasActionId);
}
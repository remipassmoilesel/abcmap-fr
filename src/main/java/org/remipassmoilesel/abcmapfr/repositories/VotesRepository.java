package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VotesRepository extends JpaRepository<Vote, Long> {

    public List<Vote> findByDate(Date date);

    @Query("SELECT AVG(v.value) FROM Vote v")
    public Double averageVoteValue();

    @Query("SELECT v FROM Vote v ORDER BY v.date DESC")
    public List<Vote> getLasts(Pageable p);
}


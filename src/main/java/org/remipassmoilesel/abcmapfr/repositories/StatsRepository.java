package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.entities.Stats;
import org.remipassmoilesel.abcmapfr.entities.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatsRepository extends CrudRepository<Stats, Long> {

    /**
     * Find and return the stats of the specified day, if exist
     *
     * @param beginDate
     * @return
     */
    @Query("SELECT s FROM Stats s WHERE s.date BETWEEN :beginDate AND :endDate")
    public List<Stats> findBetween(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    @Query("SELECT s FROM Stats s ORDER BY s.date DESC")
    public List<Stats> getLasts(Pageable p);

}


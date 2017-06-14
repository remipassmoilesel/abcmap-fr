package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.StatsOfTheDay;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatsOfTheDayRepository extends CrudRepository<StatsOfTheDay, Long> {

    /**
     * Find and return the stats of the specified day, if exist
     *
     * @param beginDate
     * @return
     */
    @Query("SELECT s FROM StatsOfTheDay s WHERE s.date BETWEEN :beginDate AND :endDate")
    public List<StatsOfTheDay> findBetween(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}


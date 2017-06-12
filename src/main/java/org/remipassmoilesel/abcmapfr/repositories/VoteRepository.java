package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    List<Vote> findByDate(Date date);
}


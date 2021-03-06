package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.entities.Subscription;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m ORDER BY m.date DESC")
    public List<Message> getLasts(Pageable p);

}


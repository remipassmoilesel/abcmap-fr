package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.entities.SoftwareUtilisation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SoftwareUtilisationRepository extends JpaRepository<SoftwareUtilisation, Long> {

    @Query("SELECT m FROM SoftwareUtilisation m ORDER BY m.date DESC")
    public List<SoftwareUtilisation> getLasts(Pageable p);

}


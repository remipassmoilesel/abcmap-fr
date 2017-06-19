package org.remipassmoilesel.abcmapfr.repositories;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

import org.remipassmoilesel.abcmapfr.entities.UpdateInformation;
import org.remipassmoilesel.abcmapfr.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UpdateInformationRepository extends JpaRepository<UpdateInformation, Long> {

    @Query("SELECT i FROM UpdateInformation i ORDER BY i.date DESC")
    public List<UpdateInformation> getLasts(Pageable p);

    @Query("SELECT i FROM UpdateInformation i WHERE codeVersion >= ?1 ORDER BY i.date DESC")
    public List<UpdateInformation> getForCodeVersion(int i);

}


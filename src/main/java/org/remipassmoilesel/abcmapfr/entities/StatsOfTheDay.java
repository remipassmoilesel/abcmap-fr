package org.remipassmoilesel.abcmapfr.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
@Entity
public class StatsOfTheDay {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String content;

    public StatsOfTheDay(){

    }

    public StatsOfTheDay(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatsOfTheDay that = (StatsOfTheDay) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, content);
    }
}

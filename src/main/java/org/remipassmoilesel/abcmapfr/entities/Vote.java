package org.remipassmoilesel.abcmapfr.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Integer value;
    private Date date;

    protected Vote() {}

    public Vote(Integer value, Date date) {
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) &&
                Objects.equals(value, vote.value) &&
                Objects.equals(date, vote.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date);
    }
}

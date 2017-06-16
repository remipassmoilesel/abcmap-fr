package org.remipassmoilesel.abcmapfr.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 16/06/17.
 */

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String mail;

    public Subscription(Date date, String mail) {
        this.date = date;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", date=" + date +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, mail);
    }
}

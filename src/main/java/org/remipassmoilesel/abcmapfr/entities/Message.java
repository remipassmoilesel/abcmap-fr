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
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String message;
    private Date date;

    public Message(){

    }

    public Message(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(date, message1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}

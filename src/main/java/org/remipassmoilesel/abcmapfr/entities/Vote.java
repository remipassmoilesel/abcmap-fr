package org.remipassmoilesel.abcmapfr.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer value;
    private Date date;

    @Column(columnDefinition = "VARCHAR(300)")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    private String language;

    private String anonymRemoteAddr;

    protected Vote() {
    }

    public Vote(Integer value, Date date) {
        this.value = value;
        this.date = date;
    }

    public Vote(Integer value, Date date, String url) {
        this.value = value;
        this.date = date;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAnonymRemoteAddr() {
        return anonymRemoteAddr;
    }

    public void setAnonymRemoteAddr(String anonymRemoteAddr) {
        this.anonymRemoteAddr = anonymRemoteAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) &&
                Objects.equals(value, vote.value) &&
                Objects.equals(date, vote.date) &&
                Objects.equals(url, vote.url) &&
                Objects.equals(userAgent, vote.userAgent) &&
                Objects.equals(language, vote.language) &&
                Objects.equals(anonymRemoteAddr, vote.anonymRemoteAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date, url, userAgent, language, anonymRemoteAddr);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", url='" + url + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", language='" + language + '\'' +
                ", anonymRemoteAddr='" + anonymRemoteAddr + '\'' +
                '}';
    }
}

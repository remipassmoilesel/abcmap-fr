package org.remipassmoilesel.abcmapfr.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 19/06/17.
 */
@Entity
public class SoftwareUtilisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    private String language;

    private String anonymRemoteAddr;

    private String version;

    public SoftwareUtilisation() {
    }

    public SoftwareUtilisation(Date date, String userAgent, String language, String anonymRemoteAddr, String version) {
        this.date = date;
        this.userAgent = userAgent;
        this.language = language;
        this.anonymRemoteAddr = anonymRemoteAddr;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareUtilisation that = (SoftwareUtilisation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userAgent, that.userAgent) &&
                Objects.equals(language, that.language) &&
                Objects.equals(anonymRemoteAddr, that.anonymRemoteAddr) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userAgent, language, anonymRemoteAddr, version);
    }

    @Override
    public String toString() {
        return "SoftwareUtilisation{" +
                "id=" + id +
                ", userAgent='" + userAgent + '\'' +
                ", language='" + language + '\'' +
                ", anonymRemoteAddr='" + anonymRemoteAddr + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

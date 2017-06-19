package org.remipassmoilesel.abcmapfr.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 19/06/17.
 */
@Entity
public class UpdateInformation {

    private static final HashMap<String, Integer> versions = new HashMap<>();

    static {
        versions.put("1.0", 1);
        versions.put("1.01", 2);
        versions.put("1.02", 3);
        versions.put("1.03", 4);
        versions.put("dev", 0);
    }

    public static Integer getCodeVersion(String version) {
        return versions.get(version);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int codeVersion;

    public UpdateInformation() {
    }

    public UpdateInformation(Date date, String content, int codeVersion) {
        this.date = date;
        this.content = content;
        this.codeVersion = codeVersion;
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

    public int getCodeVersion() {
        return codeVersion;
    }

    public void setCodeVersion(int codeVersion) {
        this.codeVersion = codeVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateInformation that = (UpdateInformation) o;
        return codeVersion == that.codeVersion &&
                Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, content, codeVersion);
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", version=" + codeVersion +
                '}';
    }
}

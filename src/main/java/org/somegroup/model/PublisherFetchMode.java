package org.somegroup.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "Publisher_fetchmode")
public class PublisherFetchMode {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publisher")
    @Fetch(FetchMode.SUBSELECT)
    private List<MagazineFetchMode> magazines;

    public PublisherFetchMode() {
    }

    public PublisherFetchMode(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MagazineFetchMode> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<MagazineFetchMode> magazines) {
        this.magazines = magazines;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}



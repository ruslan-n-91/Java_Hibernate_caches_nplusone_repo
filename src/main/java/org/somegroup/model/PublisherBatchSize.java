package org.somegroup.model;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Table(name = "Publisher_batchsize")
public class PublisherBatchSize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publisher")
    @BatchSize(size = 3)
    private List<MagazineBatchSize> magazines;

    public PublisherBatchSize() {
    }

    public PublisherBatchSize(String name) {
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

    public List<MagazineBatchSize> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<MagazineBatchSize> magazines) {
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



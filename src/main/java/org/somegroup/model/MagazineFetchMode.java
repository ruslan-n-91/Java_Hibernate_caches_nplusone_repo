package org.somegroup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Magazine_fetchmode")
public class MagazineFetchMode {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private PublisherFetchMode publisher;

    public MagazineFetchMode() {
    }

    public MagazineFetchMode(String title, int quantity, PublisherFetchMode publisher) {
        this.title = title;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PublisherFetchMode getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherFetchMode publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

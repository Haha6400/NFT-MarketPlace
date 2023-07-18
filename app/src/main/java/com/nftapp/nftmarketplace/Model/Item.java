package com.nftapp.nftmarketplace.Model;

import java.util.Date;

public class Item {
    private Long id;

    private String name;

    private String description;

    private Date dateCreated;

    private String imageLink;

    private Long price;

    private Status status;

    private Long userCollection_id;

    private Long creator_id;

    private Long category_id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "comment_id", referencedColumnName = "item_id")
//    List<Comment> comments = new ArrayList<>();


    public Item(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", status=" + status +
                '}';
    }
}

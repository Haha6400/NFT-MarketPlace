package com.nftapp.nftmarketplace.Model;

import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
    private Long id;

    private String name;

    List<Item> items = new ArrayList<>();

    private Long user_id;
}

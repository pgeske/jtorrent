package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/2/16.
 */

public class BenItem {
    private Object payload;


    /**
     * Constructor for Integer type. The rest of the constructors do this for all other
     * Bencode types.
     *
     * @param v
     */
    public BenItem(Integer v) {
        this.payload = v;
    }

    public BenItem(String s) {
        this.payload = s;
    }

    public BenItem(HashMap<String, BenItem> bd) {
        this.payload = bd;
    }

    public BenItem(ArrayList<BenItem> bl) {
        this.payload = bl;
    }

    /**
     *
     * @return This item's payload. Must be cast to a specific type
     */
    public Object getValue() {
        return this.payload;
    }

}

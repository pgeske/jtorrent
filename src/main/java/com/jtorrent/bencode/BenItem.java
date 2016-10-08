package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/2/16.
 */

public class BenItem {
    private Object payload;
    private BenItem next;   /* The BenItem following this, in this's parent container */
    private BenItem parent; /* This's parent container */


    public BenItem(Integer v) {
        this.payload = v;
        this.next = null;
        this.parent = null;
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

    public void setNext(BenItem bi) {
        this.next = bi;
    }

    public BenItem getNext() {
        return this.next;
    }

    public void setParent(BenItem parent) {
        this.parent = parent;
    }

    public BenItem getParent() {
        return this.parent;
    }

    /**
     *
     * @return This item's payload. Must be cast to a specific type
     */
    public Object getValue() {
        return this.payload;
    }

}

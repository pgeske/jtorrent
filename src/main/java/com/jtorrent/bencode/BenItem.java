package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/2/16.
 */


public class BenItem {

    private Object payload;
    private BenItem parent;
    private BenType type;


    public BenItem(Integer v) {
        this.payload = v;
        this.parent = null;
        this.type = BenType.B_INT;
    }

    public BenItem(String s) {
        this.payload = s;
        this.parent = null;
        this.type = BenType.B_STRING;
    }

    public BenItem(HashMap<String, BenItem> bd) {
        this.payload = bd;
        this.type = BenType.B_DICT;
    }

    public BenItem(ArrayList<BenItem> bl) {
        this.payload = bl;
        this.type = BenType.B_LIST;
    }

    public BenItem(BenType type) {
        this.payload = null;
        this.type = type;
    }

    public BenItem() {
        super();
    }

    public BenItem getParent() {
        return this.parent;
    }


    public Object getValue() {
        return this.payload;
    }

    public BenType getType() { return this.type; }

    public void setParent(BenItem parent) {
        this.parent = parent;
    }

    public void setValue(Object v) { this.payload = v; }


}

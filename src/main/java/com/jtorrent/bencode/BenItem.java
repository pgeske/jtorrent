package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by philip on 10/2/16.
 */


public class BenItem {

    private Object payload;
    private BenItem parent;
    private BenType type;
    private String key = "";

    public BenItem() {
        super();
    }

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


    public BenItem getParent() {
        return this.parent;
    }

    public Object getValue() {
        return this.payload;
    }

    public BenType getType() { return this.type; }

    public String getKey() { return this.key; }

    public void setParent(BenItem parent) {
        this.parent = parent;
    }

    public void setValue(Object v) { this.payload = v; }

    public void setKey(String s) { this.key = s; }

    public String toJSON() {
        String json = "";
        switch (this.type) {
            case B_INT:
                json = this.payload.toString();
                break;
            case B_STRING:
                json = (String) this.payload;
                break;
            case B_LIST:
                json += '[';
                ArrayList<BenItem> al = (ArrayList<BenItem>) this.payload;
                for (int i = 0; i < al.size(); i++) {
                    json += al.get(i).toJSON();
                    if (i != al.size() - 1) json += ',';
                }
                json += ']';
                break;
            case B_DICT:
                json += '{';
                HashMap<String, BenItem> dict = (HashMap<String, BenItem>) this.payload;
                int index = 0;
                for (String key : dict.keySet()) {
                    json += key + ':' + dict.get(key).toJSON();
                    if (index++ != dict.keySet().size() - 1) {
                        json += ',';
                    }
                }
                json += '}';
        }
        return json;
    }

}

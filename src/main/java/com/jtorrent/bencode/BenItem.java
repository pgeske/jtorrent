package com.jtorrent.bencode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/2/16.
 */


public class BenItem {

    protected Object payload;
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

    public BenItem find(String key) throws InvalidBencodeException{
        if (this.type != BenType.B_DICT) {
            throw new InvalidBencodeException("To find a value given a key, " +
                    "this BenItem must be a dictionary");
        }
        HashMap<String, BenItem> map = (HashMap<String, BenItem>) this.payload;
        return map.get(key);
    }

    public String toJSON() throws UnsupportedEncodingException{
        System.out.println(this.type);
        String json = "";
        switch (this.type) {
            case B_INT:
                json = this.payload.toString();
                break;
            case B_STRING:
                String s = (String) this.payload;
                byte[] b = s.getBytes("UTF-8");
                s = new String(b, "UTF-8");
                json = s;
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
        /*Prettify JSON*/
//        Gson gs = new GsonBuilder().setPrettyPrinting().create();
//        JsonParser jp = new JsonParser();
//        return gs.toJson(jp.parse(json));
        return json;
    }

}

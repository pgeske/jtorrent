package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by philip on 10/8/16.
 */
public class BenDecoder {

    public static BenItem decode(String bencode) throws InvalidBencodeException {
        BenItem cur = null;
        String accumulator = "";
        int accLimit = 0;
        for (int i = 0; i < bencode.length(); i++) {

            /*Establish condition for starting a new item*/
            boolean starting = false;
            boolean finishing = false;
            if (cur == null) {
                starting = true;
            } else if (cur.getType() == BenType.B_DICT || cur.getType() == BenType.B_LIST) {
                starting = true;
            }

            /* Establish condition for finishing an item */
            if (cur != null) {
                if (cur.getType() == BenType.B_STRING) {
                    if (accumulator.length() == accLimit - 1) finishing = true;
                }
                else if (bencode.charAt(i) == 'e') {
                    finishing = true;
                }
            }
            if (finishing) starting = false;


            /*Starting a new item*/
            if (starting) {
                BenItem parent = cur;
                switch (bencode.charAt(i)) {
                    case 'l':
                        cur = new BenItem(new ArrayList<BenItem>());
                        break;
                    case 'd':
                        cur = new BenItem(new HashMap<String, BenItem>());
                        break;
                    case 'i':
                        cur = new BenItem(BenType.B_INT);
                        break;
                    default:
                        if (!Character.isDigit(bencode.charAt(i))) {
                            throw new InvalidBencodeException("Invalid bencode.");
                        }
                        cur = new BenItem(BenType.B_STRING);
                        accLimit = Character.getNumericValue(bencode.charAt(i)) + 1;
                }
                cur.setParent(parent);
            }
            /*Finishing an item*/
            else if (finishing) {
                switch (cur.getType()) {
                    case B_INT:
                        cur.setValue(Integer.parseInt(accumulator));
                        break;
                    case B_STRING:
                        cur.setValue(accumulator.substring(1) + bencode.charAt(i));
                        break;
                    case B_LIST:
                        break;
                    case B_DICT:
                        break;
                }
                if (cur.getParent() == null) break;
                /*Add this finished element to its parent*/
                if (cur.getParent().getType() == BenType.B_LIST) {
                    ArrayList<BenItem> al = (ArrayList<BenItem>) cur.getParent().getValue();
                    al.add(cur);
                } else if (cur.getParent().getType() == BenType.B_DICT) {
                    /*Inserting key,value pair*/
                    if (cur.getParent().getKey().length() > 0) {
                        HashMap<String, BenItem> dict = (HashMap<String, BenItem>) cur.getParent().getValue();
                        dict.put(cur.getParent().getKey(), cur);
                        cur.getParent().setKey("");
                    }
                    /*Saving key in current BenItem*/
                    else {
                        cur.getParent().setKey(accumulator.substring(1) + bencode.charAt(i));
                    }
                }
                cur = cur.getParent();
                accumulator = "";

            }
            /*In middle of item*/
            else {
                accumulator = accumulator + bencode.charAt(i);
            }
        }
        return cur;
    }
}

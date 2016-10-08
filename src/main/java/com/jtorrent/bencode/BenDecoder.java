package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/8/16.
 */
public class BenDecoder {

    public static BenItem decode(String bencode) {
        BenItem cur;
        // 
//        String curType = null;
//        if (bencode.charAt(0) == 'l') {
//            cur = new BenItem(new ArrayList<BenItem>());
//        }
//        else if (bencode.charAt(0) == 'd') {
//            cur = new BenItem(new HashMap<String, BenItem>());
//        }
//        for (int i = 1; i < bencode.length(); i++) {
//            //
//            if (bencode.charAt(i) == 'i' && curType == null) {
//                curType = 'i';
//            }
//            elsif (bencode.charAt(i) == '')
//        }
    }
}

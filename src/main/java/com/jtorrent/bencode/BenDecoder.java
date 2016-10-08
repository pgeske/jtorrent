package com.jtorrent.bencode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by philip on 10/8/16.
 */
public class BenDecoder {

    public static BenItem decode(String bencode) {
        BenItem cur;
        BenItem result;
        int index;
        // Get initial BenItem
        switch (bencode.charAt(0)) {
            case 'l':
                cur = new BenItem(new ArrayList<BenItem>());
                break;
            case 'd':
                cur = new BenItem(new HashMap<String, BenItem>());
        }
        if (bencode.charAt(0) == 'l') {
            cur = new BenItem(new ArrayList<BenItem>());
        }
        else if (bencode.charAt(0) )

        while (cur != null) {
            // Starting new item: create new BenItem and set parent to cur

            // In the middle of item: add chars to accumulator
                //TODO

            // Finished item: store accumulated value in cur, push cur to parent, and set cur to parent
                //TODO
                // If cur's parent is null, then we must be finished decoding
        }

        return result;


    }
}

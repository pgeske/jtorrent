package com.jtorrent.bencode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by philip on 10/8/16.
 */
public class BenEncoder {

    /**
     * Encodes a BenItem as a bencode string.
     * @param bi The BenItem to be encoded.
     * @return bi encoded as bencode
     */
    public static String encode(BenItem bi) {
        String bencode = "";
        switch (bi.getType()) {
            case B_STRING:
                Integer len = bi.getString().getBytes().length;
                bencode = len.toString() + ':' + bi.getString();
                break;
            case B_INT:
                bencode = 'i' + bi.getInteger().toString() + 'e';
                break;
            case B_LIST:
                bencode += 'l';
                for (BenItem sbi: bi.getList()) {
                    bencode += encode(sbi);
                }
                bencode += 'e';
                break;
            case B_DICT:
                bencode += 'd';
                Iterator it = bi.getDictionary().entrySet().iterator();
                while (it.hasNext()) {
                    HashMap.Entry<String, BenItem> pair = (HashMap.Entry<String, BenItem>) it.next();
                    BenItem nb = new BenItem(pair.getKey());
                    bencode += encode(nb) + encode(pair.getValue());
                }
                bencode += 'e';
                break;
            default:
                break;
        }
        return bencode;
    }
}

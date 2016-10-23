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
                bencode = bi.getString().length() + ':' + bi.getString();
                break;
            case B_INT:
                bencode = bi.getInteger().toString();
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
                    bencode += pair.getKey() + pair.getValue();
                }
                bencode += 'e';
                break;
            default:
                break;
        }
        return bencode;
    }
}

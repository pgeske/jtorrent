package com.jtorrent.bencode;

import java.util.HashMap;

/**
 * Created by philip on 10/19/16.
 */
public class MetaInfo {
    private BenItem root;

    public MetaInfo(BenItem bi) throws InvalidBencodeException {
        if (bi.getType() != BenType.B_DICT) {
            throw new InvalidBencodeException("The root of a torrent file must be a dictionary.");
        }
        this.root = bi;
    }

    public BenItem getTracker() throws InvalidBencodeException {
        return root.find("announce");
    }

    public BenItem getInfo() throws InvalidBencodeException {
        return root.find("info");
    }
}

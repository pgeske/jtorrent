package com.jtorrent;

import com.jtorrent.bencode.BenDecoder;
import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.BenType;
import com.jtorrent.bencode.InvalidBencodeException;


public class App 
{
    public static void main( String[] args ) throws InvalidBencodeException
    {
        BenDecoder bd = new BenDecoder();
        String bencode = "5:abcds";
        BenItem bi = bd.decode(bencode);
        System.out.println(bi.getValue());

    }
}

package com.jtorrent;

import com.jtorrent.bencode.BenDecoder;
import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.BenType;
import com.jtorrent.bencode.InvalidBencodeException;

import java.util.ArrayList;


public class App 
{
    public static void main( String[] args ) throws InvalidBencodeException
    {
        BenDecoder bd = new BenDecoder();
        String bencode = "l2:ef3:abcli25eei4444el3:efg2:ab3:abc4:eeeel2:so3:how3:arei450eee";
        BenItem bi = bd.decode(bencode);
        System.out.println(bi.toJSON());
    }
}

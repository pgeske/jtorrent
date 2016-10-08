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
        String bencode = "l2:ef3:abci4444el3:efgee";
        BenItem bi = bd.decode(bencode);
        ArrayList<BenItem> al = (ArrayList<BenItem>) bi.getValue();
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i).getValue() + " " + al.get(i).getType());
        }
        System.out.println(bi.getType());
    }
}

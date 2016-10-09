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
        String test = "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee";
        BenItem bi = bd.decode(test);
        System.out.println(bi.toJSON());
    }
}

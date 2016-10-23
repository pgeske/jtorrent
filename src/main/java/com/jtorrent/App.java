package com.jtorrent;

import com.jtorrent.bencode.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class App 
{
    public static void main( String[] args ) throws InvalidBencodeException, FileNotFoundException, IOException
    {
        File f = new File("/Users/pgeske/Downloads/sample.torrent");
        FileInputStream fs = new FileInputStream(f);
        BenItem be = BenDecoder.decode(fs);
        String bencode = BenEncoder.encode(be);
        BenDecoder.decode(bencode);
    }
}

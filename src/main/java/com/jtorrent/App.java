package com.jtorrent;

import com.jtorrent.bencode.*;
import com.jtorrent.client.Client;
import com.jtorrent.client.peer.Peer;
import com.jtorrent.client.torrent.Torrent;
import java.io.*;
import java.security.NoSuchAlgorithmException;


public class App
{
       public static void main( String[] args )
            throws InvalidBencodeException,
            IOException, NoSuchAlgorithmException
    {
        File f = new File("C:\\Users\\Philip\\Downloads\\ubuntu-16.10-desktop-amd64.iso.torrent");
        Torrent t = new Torrent(f);
        Client c = new Client(t);
        for (Peer peer : c.getPeers()) {
            System.out.println(peer.getIp() + ":" + peer.getPort());
        }
    }
}

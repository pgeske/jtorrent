package com.jtorrent;

import com.jtorrent.bencode.*;
import com.jtorrent.client.Client;
import com.jtorrent.client.peer.Peer;
import com.jtorrent.client.peer.protocol.Handshake;
import com.jtorrent.client.torrent.Torrent;
import java.io.*;
import java.nio.ByteBuffer;
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
//        Peer[] peers = c.getPeers();
//        Handshake clientHandshake = new Handshake(t,"akdlakdiejsldifjelsk");
//        for (Peer p: peers) {
//            try {
//                Handshake handshake = p.handshake(clientHandshake);
//                if (handshake != null) {
//                    System.out.println(handshake.getPeerId());
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
        int lengthPrefix = 5;
        ByteBuffer messageBuffer = ByteBuffer.allocate(20);
        messageBuffer.putInt(lengthPrefix);
        for (byte b : messageBuffer.array()) {
            System.out.println(b);
        }
    }
}

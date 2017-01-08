package com.jtorrent;

import com.jtorrent.bencode.*;
import com.jtorrent.client.Client;
import com.jtorrent.client.peer.Peer;
import com.jtorrent.client.peer.protocol.Handshake;
import com.jtorrent.client.peer.protocol.Message;
import com.jtorrent.client.torrent.Torrent;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;


public class App
{
       public static void main( String[] args )
            throws InvalidBencodeException,
            IOException, NoSuchAlgorithmException
    {
        File f = new File("C:\\Users\\Philip\\Downloads\\ubuntu-16.10-desktop-amd64.iso.torrent");
        Torrent t = new Torrent(f);
        Client c = new Client(t);
        Peer[] peers = c.getPeers();
        // Construct client side handshake
        Handshake clientHandshake = new Handshake(t,"akdlakdiejsldifjelsk");
        // Register all peer channels into a selector
        Selector selector = Selector.open();
        for (Peer p: peers) {
            p.connect();
            SelectionKey sk = p.getConnection().register(selector, SelectionKey.OP_CONNECT);
            sk.attach(p);
        }
        while (true) {
            int readyChannels = selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                Peer p = (Peer) key.attachment();
                if (key.isValid() && key.isConnectable()) {
                    try {
                        boolean connectionFinished = p.getConnection().finishConnect();
                        if (connectionFinished) {
                            System.out.println(p.getIp());
                            key.interestOps(0);
                        }
                    } catch (ConnectException e) {
                        key.interestOps(0);
                    }
                }
                keyIterator.remove();
            }
        }

    }
}

package com.jtorrent.client.peer;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.peer.protocol.Handshake;
import com.jtorrent.client.peer.protocol.Message;
import com.jtorrent.client.torrent.Torrent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


/**
 * Created by Philip on 12/11/2016.
 */
public class Peer {
    private String id;
    private String ip;
    private Integer port;
    private SocketChannel connection;

    public Peer(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getId() { return this.id; }

    public String getIp() { return this.ip; }

    public Integer getPort() { return this.port; }

    /**
     * initializes a TCP connection via a 2-way handshake with Peer, and returns Peer's response
     * Handshake.
     * @param clientHandshake
     * @return The Handshake the Peer sends in response to the Client's handshake.
     * @throws IOException
     * @throws InvalidBencodeException
     */
    public Handshake handshake(Handshake clientHandshake) throws IOException, InvalidBencodeException {
        this.connection = SocketChannel.open(new InetSocketAddress(this.ip, this.port));
        ByteBuffer handshakeBuffer = clientHandshake.getBuffer();
        handshakeBuffer.flip();

        // Setup handshake with peer
        SocketAddress address = new InetSocketAddress(this.getIp(), this.getPort());
        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.write(handshakeBuffer);
        ByteBuffer response = ByteBuffer.allocate(Handshake.HANDSHAKE_LENGTH);
        int bytesRead = socketChannel.read(response);
        response.flip();
        if (bytesRead != -1) {
            return new Handshake(response);
        }
        return null;
    }

    /**
     * Sends a messge to the Peer via the socket channel.
     * @param message The message to be sent.
     */
    public void send(Message message) {

    }

}

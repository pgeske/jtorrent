package com.jtorrent.client.peer.protocol;

import java.nio.ByteBuffer;

/**
 * Created by Philip on 1/1/2017.
 */
public class Message {
    private int lengthPrefix;
    private int messageId;
    private byte[] payload;


    /**
     * Constructor to wrap message parameters into Message
     * @param lengthPrefix
     * @param messageId
     */
    public Message(int lengthPrefix, int messageId) {
        this.lengthPrefix = lengthPrefix;
        this.messageId = messageId;
    }

    public Message(int lengthPrefix, int messageId, byte[] payload) {
        this.lengthPrefix = lengthPrefix;
        this.messageId = messageId;
        this.payload = payload;
    }

    /**
     * Constructor to unpack ByteBuffer containing message into Message
     * @param messageBuffer
     */
    public Message(ByteBuffer messageBuffer) {
        // Parse length prefix
        byte[] lengthPrefix = new byte[4];
        messageBuffer.get(lengthPrefix);
        ByteBuffer intBuffer = ByteBuffer.wrap(lengthPrefix);
        this.lengthPrefix = intBuffer.getInt();

        // Parse message id
        this.messageId = messageBuffer.get();

        // Parse payload
        if (this.lengthPrefix - 1 > 0) {
            this.payload = new byte[this.lengthPrefix - 1];
        }
        messageBuffer.get(this.payload);
    }

    public int getLengthPrefix() { return this.lengthPrefix; }

    public int getMessageId() { return this.messageId; }

    public byte[] getPayload() { return this.payload; }

    /**
     * Constructs ByteBuffer (in READ mode) with message parameters packed into it.
     * @return
     */
    public ByteBuffer getBuffer() {
        ByteBuffer messageBuffer = ByteBuffer.allocate(this.lengthPrefix + 4);
        messageBuffer.putInt(this.lengthPrefix);
        messageBuffer.put((byte) this.messageId);
        messageBuffer.put(this.payload);
        messageBuffer.flip();
        return messageBuffer;
    }


}

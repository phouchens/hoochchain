package com.hoochchain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timestamp;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return Crypt.sha256(previousHash + Long.toString(timestamp) + data);
    }

}

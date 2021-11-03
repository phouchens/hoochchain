package com.hoochchain;

public class HoochChain {
    public static void main(String[] args) {

        final Block genesisBlock = new Block("Hi im the first block", "0");
        System.out.println("Hash for block 1 : " + genesisBlock.getHash());

        final Block secondBlock = new Block("Yo im the second block", genesisBlock.getHash());
        System.out.println("Hash for block 2 : " + secondBlock.getHash());

        final Block thirdBlock = new Block("Hey im the third block", secondBlock.getHash());
        System.out.println("Hash for block 3 : " + thirdBlock.getHash());
    }
}

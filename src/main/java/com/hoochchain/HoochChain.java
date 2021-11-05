package com.hoochchain;

import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HoochChain {
    private static int DIFFICULTY = 5;

    @Getter
    private List<Block> hoochChain;

    public HoochChain() {
        this.hoochChain = populateChain();
    }

    private List<Block> populateChain() {
        final List<Block> chain = new ArrayList<>();
        chain.add(new Block("Genesis BLock", "0"));
        chain.get(0).mineBlock(DIFFICULTY);
        chain.add(new Block("Second Block", chain.get(chain.size() - 1).getHash()));
        chain.get(1).mineBlock(DIFFICULTY);
        chain.add(new Block("Third Block", chain.get(chain.size() - 1).getHash()));
        chain.get(1).mineBlock(DIFFICULTY);
        isChainValid(chain);
        return chain;
    }

    public String getHoochChainAsString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(hoochChain);
    }


    public Boolean isChainValid(final List<Block> hoochChain) {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < hoochChain.size(); i++) {
            currentBlock = hoochChain.get(i);
            previousBlock = hoochChain.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        System.out.println("HoochChain is valid");
        return true;
    }
}

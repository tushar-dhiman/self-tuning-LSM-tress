package com.tushar.lsm;

public class TierManager {

    private final int hotThreshold;

    public TierManager(int hotThreshold) {
        this.hotThreshold = hotThreshold;
    }

    public void evaluate(SSTable table) {
        if (table.getAccessCount() >= hotThreshold) {
            table.setTier(StorageTier.HOT);
        } else {
            table.setTier(StorageTier.COLD);
        }
    }
}

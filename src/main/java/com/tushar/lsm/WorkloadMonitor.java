package com.tushar.lsm;

public class WorkloadMonitor {

    private int reads = 0;
    private int writes = 0;

    public void recordRead() {
        reads++;
    }

    public void recordWrite() {
        writes++;
    }

    public boolean isWriteHeavy() {
        return writes > reads;
    }

    public void reset() {
        reads = 0;
        writes = 0;
    }
}

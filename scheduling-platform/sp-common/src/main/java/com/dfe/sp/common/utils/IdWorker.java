package com.dfe.sp.common.utils;

public class IdWorker {
    private final long workerId = 12L;
    private final long twepoch = 1303895660503L;
    private long sequence = 0L;
    private final long workerIdBits = 10L;
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
    private final long sequenceBits = 12L;

    private final long workerIdShift = this.sequenceBits;
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

    private long lastTimestamp = -1L;
    private static IdWorker idWorker = new IdWorker();


//    public IdWorker(long workerId) {
//        super();
//        if (workerId > this.maxWorkerId || workerId < 0) {
//            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
//                    this.maxWorkerId));
//        }
//        this.workerId = workerId;
//    }

    public IdWorker() {

    }


    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        }
        else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            System.out.println((String
                    .format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            (this.lastTimestamp - timestamp))));
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.twepoch << this.timestampLeftShift | this.workerId << this.workerIdShift
                | this.sequence;
    }

    public String nextStrId() {
        return String.valueOf(nextId());
    }
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }


    private long timeGen() {
        return System.currentTimeMillis();
    }
    public static IdWorker getIdWork() {
        return idWorker;
    }
}

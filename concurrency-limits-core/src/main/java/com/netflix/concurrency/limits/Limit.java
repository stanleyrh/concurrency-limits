package com.netflix.concurrency.limits;

/**
 * Contract for an algorithm that calculates a concurrency limit based on 
 * rtt measurements
 */
public interface Limit {
    /**
     * @return Current estimated limit
     */
    int getLimit();
    
    /**
     * Update the concurrency limit using a new rtt sample
     * 
     * @param rtt Minimum RTT sample for the last window
     * @param maxInFlight Maximum number of inflight requests observed in the sampling window
     */
    void update(long rtt, int maxInFlight);
    
    /**
     * The request failed and was dropped due to being rejected by an external limit
     * or hitting a timeout.  Loss based implementations will likely reduce the limit
     * aggressively when this happens.
     */
    void drop();
}

package com.lazulite.rse.domain.enumeration;

/**
 * The OrderStatus enumeration.
 */
public enum OrderStatus {
    PendingPayment, PendingConfirming, Stocking, Shipped, Arrived, ToBeReturned, Returning, Completed, BeExpired, HasBoughtOut, Closed
}

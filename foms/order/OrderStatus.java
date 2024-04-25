package foms.order;

import java.io.Serializable;

public enum OrderStatus {
    PENDING,
    PREPARING,
    READYTOPICKUP,
    COMPLETED,
    CANCELLED;

    }
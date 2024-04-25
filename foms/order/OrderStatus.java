package foms.order;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    PENDING,
    PREPARING,
    READYTOPICKUP,
    COMPLETED,
    CANCELLED;

    }
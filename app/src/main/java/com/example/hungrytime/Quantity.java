package com.example.hungrytime;

public class Quantity {
    private String metric;
    private String quantity;

    public Quantity(String metric, String quantity){
        this.metric = metric;
        this.quantity = quantity;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public int getQuantity() {
        return Integer.parseInt(this.quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

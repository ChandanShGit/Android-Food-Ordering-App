package com.canteen.chandan.mcafeteria.Beans;


public class OrdersMap {
    private int order_id;
    private int food_id;
    private int customer_id;
    private int qty;
    private int net_pay;
    private String paymentStatus;
    private String deliverStatus;
    private String order_Date;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getNet_pay() {
        return net_pay;
    }

    public void setNet_pay(int net_pay) {
        this.net_pay = net_pay;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }
}
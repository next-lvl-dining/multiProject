package domain;

public class OrderStatus {
    private String status;

    public OrderStatus() { }

    public OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

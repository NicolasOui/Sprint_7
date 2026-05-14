package ru.praktikum.scooter.model;

public class ListOfOrders {

    private int courierId;
    private String nearestStation;
    private int number;
    private int page;

    public ListOfOrders(int courierId, String nearestStation, int number, int page) {
        this.courierId = courierId;
        this.nearestStation = nearestStation;
        this.number = number;
        this.page = page;
    }

    public ListOfOrders() {
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

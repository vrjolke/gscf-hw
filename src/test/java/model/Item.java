package model;

public record Item(String name, double price, String category) {

    @Override
    public String toString() {
        return "name=" + name + ", price=" + price + ", category=" + category;
    }
}

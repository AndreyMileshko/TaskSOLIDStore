package Store.Items.Clothes;

import Store.Items.Item;

public class Clothes extends Item implements Wearable {

    private final Size size;

    public Clothes(String title, int price, Size size) {
        super(title, price);
        this.size = size;
    }
}

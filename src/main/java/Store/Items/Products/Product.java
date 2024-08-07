package Store.Items.Products;

import Store.Items.Item;
//Класс Product(так же как и классы Electronics и Clothes) наследуются от класса Item.
//Соответственно для добавления новых типов товаров необходимо лишь наследоваться от абстрактного класса Item
//и в случае необходимости расширять его.
//(Open-closed principle)


public class Product extends Item implements Edible {

    private final ProductType productType;

    public Product(String title, int price, ProductType productType) {
        super(title, price);
        this.productType = productType;
    }
}


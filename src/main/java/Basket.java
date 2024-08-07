import java.util.HashMap;

import java.util.Map;

import Store.Items.Item;
import Store.Store;

import static Store.Store.store;

public class Basket {
    private final Map<Item, Integer> basketItems = new HashMap<>();
    private Map<Item, Integer> basketItemsCash;

    public void addItemToBasketByArticleOrTitle(String articleOrTitle, Integer quantity) {
        Item addedItem = Store.articleOrTitleSearch(articleOrTitle);
        if (addedItem == null) {
            System.out.println("\nТовар не найден.");
            return;
        }
        Integer storeQuantity = store.get(addedItem);
        if (storeQuantity >= quantity) {
            if (basketItems.containsKey(addedItem)) {
                basketItems.put(addedItem, basketItems.get(addedItem) + quantity);
            } else {
                basketItems.put(addedItem, quantity);
            }
            store.put(addedItem, storeQuantity - quantity);
        } else {
            System.out.println("\nНедостаточно товара в магазине. Остаток товара: " + storeQuantity);
        }
    }

    public void removeItemOutOfTheBasketByTitleOrArticle(String articleOrTitle, Integer quantity) {
        Item removeItem = null;
        for (Map.Entry<Item, Integer> entry : basketItems.entrySet()) {
            Item item = entry.getKey();
            if (item.getArticle().equals(articleOrTitle)) removeItem = item;
            if (item.getTitle().equals(articleOrTitle)) removeItem = item;
        }
        if (removeItem == null) {
            System.out.println("\nТовар не найден.");
            return;
        }
        if (basketItems.get(removeItem) < quantity) {
            System.out.println("\nВведено кол-во большее чем добавлено в корзину. Кол-во товара в корзине: " + basketItems.get(removeItem));
            return;
        }
        basketItems.put(removeItem, basketItems.get(removeItem) - quantity);
        Store.addItemToList(removeItem, quantity);
    }

    public void showBasketList() {
        int counter = 1;
        int totalPrice = 0;
        System.out.println("\nСписок товаров в корзине: \nНаименование     цена       артикул         кол-во     стоимость");
        for (Map.Entry<Item, Integer> entry : basketItems.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            if (quantity > 0) {
                System.out.printf("%s. %10s %10s %20s %10s %10S\n",
                        counter++, item.getTitle(), item.getPrice(), item.getArticle(), quantity, item.getPrice() * quantity);
                totalPrice = totalPrice + quantity * item.getPrice();
            }
        }
        System.out.printf("Общая стоимость товаров: %d руб.\n", totalPrice);
    }

    public void payForTheItem() {
        basketItemsCash = new HashMap<>(basketItems);
        basketItems.clear();
    }

    public Map<Item, Integer> getBasketItemsCash() {
        return basketItemsCash;
    }
}

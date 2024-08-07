package Store;

import Store.Items.Clothes.Clothes;
import Store.Items.Clothes.Size;
import Store.Items.Electronics.DeviceType;
import Store.Items.Electronics.Electronics;
import Store.Items.Item;
import Store.Items.Products.Product;
import Store.Items.Products.ProductType;

import java.util.HashMap;
import java.util.Map;

//Класс Store содержит лишь методы для работы с наполнением магазина(метод addItemToList),
//отображением списка доступных товаров(showItemsList),
//отображения отзывов и рейтинга товаров магазина(showRatingItem, showRecallItem)
//исключением может быть метод отображения возможных операций, но большинство из них так или иначе связаны с магазином.
//(Single-Responsibility principle)
public class Store {
    //Классы Product, Electronics и Clothes могут играть роль своего родителя класса Item за счет наследования и полиморфизма
    //(Liskov substitution principle)
    public static final Map<Item, Integer> store = new HashMap<>();

    public static void showItemsList() {
        int counter = 1;
        System.out.println("\nСписок доступных для покупки товаров: \nНаименование     цена       артикул         кол-во");
        for (Map.Entry<Item, Integer> entry : store.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            if (quantity > 0) {
                System.out.printf("%s. %10s %10s %20s %10s\n", counter++, item.getTitle(), item.getPrice(), item.getArticle(), quantity);
            }
        }
    }

    //Метод для поиска товара в магазине(мапе) по артикулу или названию используется в коде 3 раза (DRY)
    public static Item articleOrTitleSearch(String articleOrTitle) {
        Item result = null;
        for (Map.Entry<Item, Integer> entry : store.entrySet()) {
            Item item = entry.getKey();
            if (item.getArticle().equals(articleOrTitle)) result = item;
            if (item.getTitle().equals(articleOrTitle)) result = item;
        }
        return result;
    }


    public static void addItemToList(Item item, Integer quantity) {
        if (store.containsKey(item)) {
            Integer quantityStore = store.get(item);
            store.put(item, quantityStore + quantity);
        } else {
            store.put(item, quantity);
        }
    }

    public static void showOperations() {
        System.out.println("\nСписок возможных операций: \n" +
                "1) Просмотреть список товаров;\n" +
                "2) Добавить товар в корзину по наименованию или артикулу;\n" +
                "3) Удалить товар из корзины по наименованию или артикулу;\n" +
                "4) Просмотр товаров в корзине;\n" +
                "5) Оплатить выбранные товары;\n" +
                "6) Оценить купленные товары;\n" +
                "7) Оставить отзыв о товаре;\n" +
                "8) Просмотр рейтинга товара;\n" +
                "9) Просмотр отзывов о товаре;\n" +
                "\"End\" для завершения покупок");
    }

    public static void showRatingItem(String articleOrTitle) {
        Item item = articleOrTitleSearch(articleOrTitle);
        if (item != null) {
            System.out.printf("Средняя оценка товара \"%s\" - %s\n", item.getTitle(), item.getRating());
        } else {
            System.out.println("Товар не найден.\n");
        }
    }

    public static void showRecallItem(String articleOrTitle) {
        Item item = articleOrTitleSearch(articleOrTitle);
        if (item != null) {
            if (!item.getRecalls().isEmpty()) {
                int count = 1;
                for (String recall : item.getRecalls()) {
                    System.out.println(count++ + ". " + recall);
                }
            } else {
                System.out.println("Отзывов о данном товаре пока нет. Оставьте свой отзыв.");
            }
        } else {
            System.out.println("Товар не найден.\n");
        }
    }

    public static void fillingStoreWithStandardItems() {
        Product milk = new Product("Молоко", 100, ProductType.MILK_PRODUCTS);
        Product bread = new Product("Батон", 50, ProductType.BAKERY_PRODUCTS);
        Product chickenBreast = new Product("Грудка", 400, ProductType.MEAT);
        Product pepsi = new Product("Пепси", 120, ProductType.DRINKS);
        Product apple = new Product("Яблоко", 250, ProductType.FRUITS);
        Product сucumber = new Product("Огурец", 200, ProductType.VEGETABLES);
        Electronics iphone = new Electronics("Айфон", 999, DeviceType.SMARTPHONE);
        Clothes hat = new Clothes("Шляпа", 700, Size.S);
        addItemToList(milk, 20);
        addItemToList(bread, 30);
        addItemToList(chickenBreast, 15);
        addItemToList(pepsi, 50);
        addItemToList(apple, 12);
        addItemToList(сucumber, 23);
        addItemToList(iphone, 5);
        addItemToList(hat, 1);
    }
}
import Store.Items.Item;

import java.util.Map;
import java.util.Scanner;

import static Store.Store.*;

public class Main {
    public static void main(String[] args) {
        fillingStoreWithStandardItems();

        System.out.println("Приветствуем вас в нашем магазине!");
        Scanner sc = new Scanner(System.in);
        Basket basket = new Basket();

        boolean cont = true;
        while (cont) {
            showOperations();
            System.out.print("\nВвод команды: ");
            String operation = sc.nextLine();
            if (operation.equalsIgnoreCase("End")) {
                System.out.println("Спасибо за покупки. Хорошего вам дня!");
                cont = false;
                continue;
            }
            switch (operation) {
                case "1":
                    showItemsList();
                    break;
                case "2":
                    System.out.print("Введите наименование товара или артикул для добавления: ");
                    String articleOrTitle = sc.nextLine();
                    System.out.print("Введите кол-во товара: ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод.");
                        continue;
                    }
                    basket.addItemToBasketByArticleOrTitle(articleOrTitle, quantity);
                    break;
                case "3":
                    System.out.print("Введите наименование товара или артикул для удаления: ");
                    String articleOrTitleRemove = sc.nextLine();
                    System.out.print("Введите кол-во товара: ");
                    int quantityRemove;
                    try {
                        quantityRemove = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод.");
                        continue;
                    }
                    basket.removeItemOutOfTheBasketByTitleOrArticle(articleOrTitleRemove, quantityRemove);
                    break;
                case "4":
                    basket.showBasketList();
                    break;
                case "5":
                    basket.payForTheItem();
                    System.out.println("Товары успешно оплачены. Корзина пуста.");
                    break;
                case "6":
                    if (basket.getBasketItemsCash() != null) {
                        for (Map.Entry<Item, Integer> entry : basket.getBasketItemsCash().entrySet()) {
                            Item item = entry.getKey();
                            System.out.print("Оцените \"" + item.getTitle() + "\": ");
                            try {
                                item.setRating(Double.parseDouble(sc.nextLine()));
                            } catch (NumberFormatException e) {
                                System.out.println("Неверный ввод.");
                            }
                        }
                    } else {
                        System.out.println("Пока оценить нечего. Нужно произвести покупку товаров.");
                    }
                    break;
                case "7":
                    if (basket.getBasketItemsCash() != null) {
                        for (Map.Entry<Item, Integer> entry : basket.getBasketItemsCash().entrySet()) {
                            Item item = entry.getKey();
                            System.out.print("Отзыв для \"" + item.getTitle() + "\": ");
                            item.addRecall(sc.nextLine());
                        }
                    } else {
                        System.out.println("Пока комментировать нечего. Нужно произвести покупку товаров.");
                    }
                    break;
                case "8":
                    System.out.print("Введите наименование или артикул товара для просмотра рейтинга: ");
                    showRatingItem(sc.nextLine());
                    break;

                case "9":
                    System.out.print("Введите наименование или артикул товара чтобы просмотреть отзывы: ");
                    showRecallItem(sc.nextLine());
                    break;
                default:
                    System.out.println("Неверный ввод.");
            }
        }
    }
}


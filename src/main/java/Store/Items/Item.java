package Store.Items;

import Store.Sellable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Item implements Sellable {
    private final String title;
    private final int price;
    private final String article;
    private double rating;
    private final List<String> recalls;

    private static final int articleNumberMin = 100_000;        //Магическое число для формирования артикула товара (Magics)
    private static final int articleNumberMax = 900_000;        //Магическое число для формирования артикула товара (Magics)
    private static final List<Double> ratingCache = new ArrayList<>();

    public Item(String title, int price) {
        this.title = title;
        this.price = price;
        int articleNumber = new Random().nextInt(articleNumberMax) + articleNumberMin;
        article = articleNumber + title.substring(0, title.length() / 2) + price;
        rating = 0.0;
        recalls = new ArrayList<>();
    }

    public void addRecall(String recall) {
        recalls.add(recall);
    }

    public void setRating(double rating) {
        ratingCache.add(rating);
        this.rating = ratingCache.stream().mapToDouble(d -> d).sum() / ratingCache.size();
    }

    public double getRating() {
        return rating;
    }

    public List<String> getRecalls() {
        return recalls;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getArticle() {
        return article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price && Objects.equals(title, item.title) && Objects.equals(article, item.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, article);
    }
}
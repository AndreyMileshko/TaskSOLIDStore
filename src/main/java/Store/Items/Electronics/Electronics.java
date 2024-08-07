package Store.Items.Electronics;

import Store.Items.Item;

//У нас есть интерфейс Sellable(продаваемые) который в свою очередь делится на интерфейсы Edible(Съедобные),
// Electric(Электрические) и Wearable(Надеваемые, в смысле одежды) и соответствующие для данных интерфейсов классы:
//Product, Electronics, Clothes.
public class Electronics extends Item implements Electric {

    DeviceType deviceType;

    public Electronics(String title, int price, DeviceType deviceType) {
        super(title, price);
        this.deviceType = deviceType;
    }
}

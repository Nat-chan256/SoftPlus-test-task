package ru.moskovka.softplus;

public class App {
    /**
     * Метод "скидка". Применяет скидку discount к цене price, начиная с позиции offset
     * на количество позиций readLength. Новые цены округляем “вниз”,
     * до меньшего целого числа.
     * @param price - исходные цены, цена должна быть больше нуля
     * @param discount - % скидки, должен попадать в диапазон от 1 до 99
     * @param offset - номер позиции, с которой нужно применить скидку, должен быть больше или равен нулю
     * @param readLength - количество позиций, к которым нужно применить скидку, должно быть больше нуля
     * @return - массив новых цен.
     */
    public int[] decryptData( int[] price,
                              int discount,
                              int offset,
                              int readLength) {
        if (discount < 1 || discount > 99)
            throw new IllegalArgumentException("Значение скидки (параметр discount) должно входить в диапазон [1;99]");
        if (offset < 0)
            throw new IllegalArgumentException("Значение отступа (параметр offset) должно быть больше либо равно 0");
        if (readLength <= 0)
            throw new IllegalArgumentException("Значение количества позиций (параметр readLength) " +
                    "должно быть больше 0");
        for (int i = 0; i < price.length; ++i)
            if (price[i] <= 0)
                throw new IllegalArgumentException("Значение цены (параметр price[i]) должно быть больше 0");

        int resultArraySize = (readLength + offset > price.length)? price.length - offset : readLength;

        if (resultArraySize <= 0)
            return new int[]{};

        int[] priceWithDiscount = new int[resultArraySize];
        for (int i = offset; i < price.length && i < offset + readLength; ++i)
            priceWithDiscount[i - offset] = (int) (price[i] - price[i] * (discount / 100.0));

        return priceWithDiscount;
    }
}

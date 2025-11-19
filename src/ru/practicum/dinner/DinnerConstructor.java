package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    //Хранилище блюд: ключ — тип блюда (например, "Суп"), значение — список названий блюд этого типа
    HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();
    //Класс random поможет сделать произвольные сочетания блюд для комбо набора
    Random random = new Random();

    /* Метод добавления блюда. Если указанный тип блюда существует, то добавляем блюдо в него. Если указанного типа блюд
    нет, то создаем указанный тип и добавляем в него указанное блюдо */
    public String addNewDish(String dishType, String dishName) {
        //Переменная для списка блюд
        ArrayList<String> dishesForType = new ArrayList<>();
        String result;
        //Проверяем наличие полученного типа блюд
        if (dinnersByType.containsKey(dishType)) {
            dishesForType = dinnersByType.get(dishType);
        } else {
            dinnersByType.put(dishType, dishesForType);
        }

        if (!dishesForType.contains(dishName)) {
            dishesForType.add(dishName);
            result = "success";
        } else {
            result = "dishAlreadyAdded";
        }

        return result;
    }

    //Метод для генерирования вариантов комбинации блюд. Метод возвращает список комбо наборов.
    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        //Пустой список для хранения получившихся комбинаций блюд
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        for (int i = 1; i <= comboNumber; i++) {
            /* В цикле вызываем метод generateCombo для генерации одного комбо набора со случайными позициями переданных
            типов блюд */
            ArrayList<String> combo = generateCombo(dishTypes);
            combos.add(combo);
        }
        return combos;
    }

    //Метод для проверки наличия типа блюда
    public boolean checkType(String type) {
        //Если хранилище уже содержит такой тип блюд - вернём true
        return dinnersByType.containsKey(type);
    }

    //Метод для генерирования одной комбинации блюд
    private ArrayList<String> generateCombo(ArrayList<String> dishTypes) {
        ArrayList<String> selectedDishes = new ArrayList<>();
        for (String dishType: dishTypes) {
            //Достаём из хранилища варианты блюд по типу
            ArrayList<String> availableDishes = dinnersByType.get(dishType);
            //Вызываем метод getRandomDish для получения случайного блюда из получившегося списка блюд необходимого типа
            String selectedDish = getRandomDish(availableDishes);
            //Добавляем блюдо в комбо набор
            selectedDishes.add(selectedDish);
        }
        return selectedDishes;
    }

    //Метод получения случайного блюда из списка полученных блюд
    private String getRandomDish(ArrayList<String> availableDishes) {
        //Получаем общее количество переданных блюд
        int numberOfDishesForType = availableDishes.size();
        //Генерируем случайное число от 0 до (кол-во блюд - 1), чтобы выбрать случайное блюдо
        int dishIndex = random.nextInt(numberOfDishesForType);
        //Выбираем произвольное блюдо по индексу
        String selectedDish = availableDishes.get(dishIndex);

        return selectedDish;
    }

}

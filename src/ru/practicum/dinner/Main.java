package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> addNewDish();
                case "2" -> generateDishCombo();
                case "3" -> {
                    return;
                }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }

    //Метод вызова меню
    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    /* Метод добавления блюда. Указывается тип блюда и само блюдо, после чего вызываем метод addNewDish класса
    DinnerConstructor. */
    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // добавьте новое блюдо, с помощью метода DinnerConstructor addNewDish
        String result = dc.addNewDish(dishType, dishName);
        if (result.equals("success")) {
            System.out.println("Блюдо " + dishName + " добавлено в раздел " + dishType + "!");
        } else if (result.equals("dishAlreadyAdded")) {
            System.out.println("В разделе " + dishType + " уже есть блюдо " + dishName +". Попробуйте добавить " +
                    "другое блюдо в данный раздел, либо попробуйте добавить в другой раздел данное блюдо.");
        }
    }

    /* Метод генерации комбо блюд.
    Указывается необходимое количество комбо наборов и типы блюд, из которых должен состоять каждый комбо набор.
    Каждый указанный тип проверяется на наличие через метод checkType класса DinnerConstructor.
    Для получения комбо наборов вызывается метод generateCombos класса DinnerConstructor, после чего наборы выводятся в
    консоль */
    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода " +
                "введите пустую строку");
        String nextItem = scanner.nextLine();

        ArrayList<String> selectedTypes = new ArrayList<>();
        //проверка на пустую строку
        while (!nextItem.isEmpty()) {
            //Проверка наличия указанного типа блюд
            if (dc.checkType(nextItem)) {
                /* Если указанный тип блюда есть, то добавляем его в список, который затем передадим в метод на
                получение комбо наборов */
                selectedTypes.add(nextItem);
            } else {
                System.out.println("Такой тип блюд мы еще не умеем готовить. Попробуйте что-нибудь другое!");
            }
            nextItem = scanner.nextLine();
        }

        // Вызываем метод generateCombos класса DinnerConstructor на генерацию и получение комбо наборов
        ArrayList<ArrayList<String>> generatedCombos = dc.generateCombos(numberOfCombos, selectedTypes);
        // Вывод полученных комбо наборов
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбинация " + (i + 1));
            System.out.println(generatedCombos.get(i));
        }
    }
}

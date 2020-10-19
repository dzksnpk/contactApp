package com.company;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

class Contact {
    protected static AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private String name;
    private String surname;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    protected static String validateNumber(String number) {
        if (number.matches("^[+]?(\\w{1}[\\s-]?)?(\\w{2,}[\\s-]?)?([(]?\\w{2,}[)]?[\\s-]?)?(\\w{2,}[\\s-]?)*")) {
            return number;
        } else {
            return "[no number]";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Contact(Integer id, String name, String surname, String number) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.number = validateNumber(number);
    }

    @Override
    public String toString() {
        return id + ". " + name +
                " " + surname +
                ", " + number;
    }
}

public class Main {
    private static AtomicInteger count = new AtomicInteger(0);
    public static Main instance;
    public List<Contact> contacts;
    Scanner scanner;
    int record;
    String field;
    String option;

    public static void main(String[] args) {

        instance = new Main();
        instance.init();
        instance.run();

    }
    private void init() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    private void run() {

        System.out.println("Enter action (add, remove, edit, count, list, exit):");

        option = scanner.next();

        switch (option) {
            case "add":
                addContact();
                break;
            case "remove":
                removeContact();
                break;
            case "edit":
                editContacts();
                break;
            case "count":
                countContacts();
                break;
            case "list":
                listContacts();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                instance.run();
                break;
        }

    }

    private void addContact() {
        Integer id = 0;
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Surname:");
        String surname = scanner.next();
        System.out.println("phone number:");
        scanner.nextLine();
        String number = scanner.nextLine();
        contacts.add(new Contact(id, name, surname, number));
        System.out.println("The record added.");
        instance.run();
    }
    private void removeContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
            instance.run();
        } else {
            for (Contact current : contacts) {
                System.out.println(current);
            }
            System.out.println("Select a record:");
            record = scanner.nextInt();
            if ( record < contacts.size()) {
                contacts.remove(record - 1);
                for (Contact current : contacts) {
                    current.setId(count.incrementAndGet());
                }
            } else {
                System.out.println("No record found.");
                instance.run();
            }
            System.out.println("The record removed!");
            instance.run();
        }
    }
    private void editContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
            instance.run();
        } else {
            for (Contact current : contacts) {
                System.out.println(current);
            }
            System.out.println("Select a record:");
            record = scanner.nextInt();
            System.out.println("Select a field (name, surname, number):");

            field = scanner.next();

            switch (field) {
                case "name":
                    String name = scanner.next();
                    contacts.get(record - 1).setName(name);
                    System.out.println("The record updated!");
                    break;
                case "surname":
                    String surname = scanner.next();
                    contacts.get(record - 1).setSurname(surname);
                    System.out.println("The record updated!");
                    break;
                case "number":
                    scanner.nextLine();
                    String number = scanner.nextLine();
                    contacts.get(record - 1).setNumber(Contact.validateNumber(number));
                    System.out.println("The record updated!");
                    break;
                default:
                    System.out.println("Not existing field");
            }

            instance.run();
        }
    }

    private void countContacts(){
        System.out.println("The PhoneBook has " + contacts.size() + " records.");
        instance.run();
    }

    private void listContacts(){
        for(Contact current : contacts) {
            System.out.println(current);
        }
        instance.run();
    }

}

import java.util.Scanner;
import java.util.Vector;

class Pizza {
    private int pizzaId;
    private String name;
    private double price;

    public Pizza(int pizzaId, String name, double price) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.price = price;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return pizzaId + ". " + name + " - INR " + price;
    }
}

class Topping {
    private String name;
    private double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - INR " + price;
    }
}

class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class PizzaOrder {
    private Pizza pizza;
    private String size;
    private int quantity;
    private Vector<Topping> toppings;
    private double totalPrice;

    public PizzaOrder(Pizza pizza, String size, int quantity, Vector<Topping> toppings, double totalPrice) {
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
        this.toppings = toppings;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder();
        summary.append("Pizza: ").append(pizza.getName())
               .append(" | Size: ").append(size)
               .append(" | Quantity: ").append(quantity)
               .append(" | Total Price: INR ").append(totalPrice)
               .append("\nToppings: ");
        for (Topping topping : toppings) {
            summary.append(topping.getName()).append(", ");
        }
        return summary.toString();
    }
}

public class PizzaShop18012V2 {
    private Vector<Pizza> menu = new Vector<>();
    private Vector<Topping> toppings = new Vector<>();
    private Scanner scanner = new Scanner(System.in);

    public PizzaShop18012V2() {
        menu.add(new Pizza(1, "Cheese Burst Pizza", 250));
        menu.add(new Pizza(2, "Veggie Pizza", 150));
        menu.add(new Pizza(3, "Paneer Pizza", 200));
        menu.add(new Pizza(4, "Pepperoni Pizza", 400));

        toppings.add(new Topping("Mushrooms", 20));
        toppings.add(new Topping("Onions", 30));
        toppings.add(new Topping("Bell Peppers", 25));
        toppings.add(new Topping("Olives", 10));
        toppings.add(new Topping("Bacon", 50));
    }

    public void start() {
        System.out.println("Welcome to Pizza Palace!");
        System.out.println("--- Login ---");
        System.out.print("Enter username: ");
        scanner.nextLine();
        System.out.print("Enter password: ");
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, phone);
        showMenu(customer);
    }

    public void showMenu(Customer customer) {
        System.out.println("\n--- Pizza Menu ---");
        for (Pizza pizza : menu) {
            System.out.println(pizza);
        }
        System.out.println("6. Exit");

        while (true) {
            System.out.print("Select a pizza by number or enter 6 to exit: ");
            int choice = scanner.nextInt();
            if (choice == 6) {
                System.out.println("Exiting the program. Thank you for visiting Pizza Palace!");
                break;
            }
            Pizza selectedPizza = getPizzaById(choice);
            if (selectedPizza != null) {
                handleOrder(selectedPizza, customer);
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private Pizza getPizzaById(int pizzaId) {
        for (Pizza pizza : menu) {
            if (pizza.getPizzaId() == pizzaId) {
                return pizza;
            }
        }
        return null;
    }

    private void handleOrder(Pizza pizza, Customer customer) {
        System.out.println("Choose pizza size:");
        System.out.println("1. Small\n2. Medium\n3. Large");
        System.out.print("Enter your choice (1, 2, or 3): ");
        int sizeChoice = scanner.nextInt();
        String size = switch (sizeChoice) {
            case 1 -> "Small";
            case 2 -> "Medium";
            case 3 -> "Large";
            default -> "Invalid";
        };

        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        Vector<Topping> selectedToppings = new Vector<>();
        System.out.println("\n--- Available Toppings ---");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ". " + toppings.get(i));
        }

        while (true) {
            System.out.print("Choose a topping (or type 0 to finish): ");
            int toppingChoice = scanner.nextInt();
            if (toppingChoice == 0) break;

            if (toppingChoice > 0 && toppingChoice <= toppings.size()) {
                selectedToppings.add(toppings.get(toppingChoice - 1));
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }

        double totalPrice = pizza.getPrice() * quantity;
        for (Topping topping : selectedToppings) {
            totalPrice += topping.getPrice() * quantity;
        }

        PizzaOrder order = new PizzaOrder(pizza, size, quantity, selectedToppings, totalPrice);
        System.out.println("\n--- Order Summary ---");
        System.out.println(order);

        System.out.println("\n--- Bill ---");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.println("Total Amount: INR " + totalPrice);
    }

    public static void main(String[] args) {
        PizzaShop18012V2 pizzaShop = new PizzaShop18012V2();
        pizzaShop.start();
    }
}

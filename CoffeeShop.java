import java.time.LocalDate;
import java.util.Scanner;

public class CoffeeShop {
    public static void main(String[] args) {
        // 创建 Scanner 对象
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0; // 总金额

        // 获取当前日期（只显示年月日）
        String currentDate = LocalDate.now().toString();
        System.out.println("Input " + currentDate + " stock");

        // 让用户输入库存（模拟）
        System.out.println("Americano:");
        scanner.nextInt();
        System.out.println("Mocha:");
        scanner.nextInt();
        System.out.println("Ice Tea:");
        scanner.nextInt();

        System.out.println("Welcome shop");

        // 购物循环
        boolean shopping = true;
        while (shopping) {
            // 显示菜单
            System.out.println("Which coffee do you want?");
            System.out.println("1. Americano - $3.5");
            System.out.println("2. Mocha - $4.0");
            System.out.println("3. Ice Tea - $2.5");

            // 读取用户输入
            String choice = scanner.next();
            String selectedCoffee = "";
            double price = 0.0;

            // 使用 if-else 判断
            if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("Americano")) {
                selectedCoffee = "Americano";
                price = 3.5;
            } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("Mocha")) {
                selectedCoffee = "Mocha";
                price = 4.0;
            } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("Ice Tea")) {
                selectedCoffee = "Ice Tea";
                price = 2.5;
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // 询问杯数
            System.out.println("How many cups?");
            int quantity = scanner.nextInt();

            // 计算价格
            double subtotal = price * quantity;
            totalAmount += subtotal;

            System.out.println("Added: " + quantity + " cups of " + selectedCoffee + " - $" + subtotal);

            // 询问是否继续购物
            while (true) {
                System.out.println("Do you want to finish shopping? (y/n)");
                String finish = scanner.next();
                if (finish.equalsIgnoreCase("y")) {
                    shopping = false;
                    break;
                } else if (finish.equalsIgnoreCase("n")) {
                    break; // 继续购物，重新显示菜单
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }
        }

        // 输出总金额
        System.out.println("Total amount: $" + totalAmount);
        System.out.println("Thank you for shopping!");
        scanner.close();
    }
}

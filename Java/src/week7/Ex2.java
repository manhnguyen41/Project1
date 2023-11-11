package week7;

// Problem: Analyze sales order of an e-commerce company

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Ex2 {
    public static int timeFormat(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        int second = Integer.parseInt(time.substring(6));

        if (hour == 23 && minute == 59 && second == 60) {
            return 0;
        }

        return hour * 3600 + minute * 60 + second;
    }
    public static void main(String[] args) throws IOException {
        class Order {
            private final String customerId;
            private final int price;
            private final String shopId;
            private final int timePoint;

            public Order(String customerId, String productId, int price, String shopId, int timePoint) {
                this.customerId = customerId;
                this.price = price;
                this.shopId = shopId;
                this.timePoint = timePoint;
            }

            public String getCustomerId() {
                return customerId;
            }

            public int getPrice() {
                return price;
            }

            public String getShopId() {
                return shopId;
            }

            public int getTimePoint() {
                return timePoint;
            }
        }

        class ListOfOrders {
            private ArrayList<Order> listOfOrders;
            private final HashMap<String, ArrayList<Order>> shopOrderMap = new HashMap<>(100000);

            private final HashMap<String, Integer> shopRevenueMap = new HashMap<>(100000); // Adjust the initial capacity

            private int totalNumberOrders;
            private int totalRevenue;

            private int []numberOrderInTime;

            public ListOfOrders() {
                this.listOfOrders = new ArrayList<>();
                this.totalNumberOrders = 0;
                this.totalRevenue = 0;
                this.numberOrderInTime = new int[86400];
            }

            public ArrayList<Order> getListOfOrders() {
                return listOfOrders;
            }

            public void setListOfOrders(ArrayList<Order> listOfOrders) {
                this.listOfOrders = listOfOrders;
            }

            public int getTotalNumberOrders() {
                return totalNumberOrders;
            }

            public void setTotalNumberOrders(int totalNumberOrders) {
                this.totalNumberOrders = totalNumberOrders;
            }

            public int getTotalRevenue() {
                return totalRevenue;
            }

            public void setTotalRevenue(int totalRevenue) {
                this.totalRevenue = totalRevenue;
            }

            public int[] getNumberOrderInTime() {
                return numberOrderInTime;
            }

            public void setNumberOrderInTime(int[] numberOrderInTime) {
                this.numberOrderInTime = numberOrderInTime;
            }

            public void addOrder(Order order) {
                listOfOrders.add(order);
                ArrayList<Order> shopOrders = shopOrderMap.getOrDefault(order.getShopId(), new ArrayList<>());
                shopOrders.add(order);
                shopOrderMap.put(order.getShopId(), shopOrders);
                totalRevenue += order.getPrice();
                totalNumberOrders++;
                numberOrderInTime[order.getTimePoint()] += order.getPrice();
                shopRevenueMap.merge(order.getShopId(), order.getPrice(), Integer::sum);
            }

            public int revenueOfShop(String shopId) {
                return shopRevenueMap.getOrDefault(shopId, 0);
            }

            public int totalConsumeOfCustomerShop(String customerId, String shopId) {
                ArrayList<Order> shopOrders = shopOrderMap.get(shopId);
                if (shopOrders == null) {
                    return 0;
                }
                int total = 0;
                for (Order order: shopOrders) {
                    if (order.getCustomerId().equals(customerId)) {
                        total += order.getPrice();
                    }
                }
                return total;
            }

            public int totalRevenueInPeriod(String fromTime, String toTime) {
                int total = 0;
                int fromTimePoint = timeFormat(fromTime);
                int toTimePoint = timeFormat(toTime);
                int []list = numberOrderInTime;
                for (int i = fromTimePoint; i <= toTimePoint; i++) {
                    total += list[i];
                }
                return total;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ListOfOrders listOfOrders = new ListOfOrders();
        StringBuilder output = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String customerId = st.nextToken();
            if (customerId.equals("#")) {
                break;
            }
            String productId = st.nextToken();
            int price = Integer.parseInt(st.nextToken());
            String shopId = st.nextToken();
            String time = st.nextToken();

            listOfOrders.addOrder(new Order(customerId, productId, price, shopId, timeFormat(time)));
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("#")) {
                break;
            }

            if (command.equals("?total_number_orders")) {
                output.append(listOfOrders.getTotalNumberOrders());
                output.append("\n");
            }

            if (command.equals("?total_revenue")) {
                output.append(listOfOrders.getTotalRevenue());
                output.append("\n");
            }

            if (command.equals("?revenue_of_shop")) {
                String shopId = st.nextToken();
                output.append(listOfOrders.revenueOfShop(shopId));
                output.append("\n");
            }

            if (command.equals("?total_consume_of_customer_shop")) {
                String customerId = st.nextToken();
                String shopId = st.nextToken();
                output.append(listOfOrders.totalConsumeOfCustomerShop(customerId, shopId));
                output.append("\n");
            }

            if (command.equals("?total_revenue_in_period")) {
                String fromTime = st.nextToken();
                String toTime = st.nextToken();
                output.append(listOfOrders.totalRevenueInPeriod(fromTime, toTime));
                output.append("\n");
            }
        }
        System.out.println(output);
        br.close();
    }
}

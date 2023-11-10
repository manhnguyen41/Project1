package week7;

// Problem: Analyze sales order of an e-commerce company

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Ex2 {
    public static String timeFormat(String time) {
        if (Integer.parseInt(time.substring(6)) == 60) {
            int second = Integer.parseInt(time.substring(0, 2)) * 3600
                    + Integer.parseInt(time.substring(3, 5)) * 60 + 60;
            if (second == 86400) {
                second = 0;
            }
            return LocalTime.ofSecondOfDay(second).toString();
        }
        return time;
    }
    public static void main(String[] args) throws IOException {
        class Order {
            private final String customerId;
            private final int price;
            private final String shopId;
            private final LocalTime timePoint;

            public Order(String customerId, String productId, int price, String shopId, LocalTime timePoint) {
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

            public LocalTime getTimePoint() {
                return timePoint;
            }
        }

        class ListOfOrders {
            private ArrayList<Order> listOfOrders;
            private final HashMap<String, ArrayList<Order>> shopOrderMap;
            private int totalNumberOrders;
            private int totalRevenue;

            public ListOfOrders() {
                this.shopOrderMap = new HashMap<>();
                this.listOfOrders = new ArrayList<>();
                this.totalNumberOrders = 0;
                this.totalRevenue = 0;
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

            public void addOrder(Order order) {
                listOfOrders.add(order);
                ArrayList<Order> shopOrders = shopOrderMap.getOrDefault(order.getShopId(), new ArrayList<>());
                shopOrders.add(order);
                shopOrderMap.put(order.getShopId(), shopOrders);
                totalRevenue += order.getPrice();
                totalNumberOrders++;
            }

            public int revenueOfShop(String shopId) {
                ArrayList<Order> shopOrders = shopOrderMap.get(shopId);
                if (shopOrders == null) {
                    return 0;
                }
                int total = 0;
                for (Order order: shopOrders) {
                    total += order.getPrice();
                }
                return total;
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
                for (Order order: listOfOrders) {
                    if (order.getTimePoint().isAfter(LocalTime.parse(fromTime))
                    && order.getTimePoint().isBefore(LocalTime.parse(toTime))) {
                        total += order.getPrice();
                    }
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

            listOfOrders.addOrder(new Order(customerId, productId, price, shopId, LocalTime.parse(timeFormat(time))));
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
                output.append(listOfOrders.totalRevenueInPeriod(timeFormat(fromTime), timeFormat(toTime)));
                output.append("\n");
            }
        }
        System.out.println(output);
        br.close();
    }
}

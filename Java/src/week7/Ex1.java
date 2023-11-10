package week7;

// Problem: Bank Transaction

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ex1 {
    public static void main(String[] args) throws IOException {
        class Transaction {
            private final String fromAccount;
            private final String toAccount;
            private final int money;

            public Transaction(String fromAccount, String toAccount, int money) {
                this.fromAccount = fromAccount;
                this.toAccount = toAccount;
                this.money = money;
            }

            public String getFromAccount() {
                return fromAccount;
            }

            public String getToAccount() {
                return toAccount;
            }

            public int getMoney() {
                return money;
            }
        }

        class ListOfAccount {
            private final ArrayList<String> accounts;

            public ListOfAccount() {
                this.accounts = new ArrayList<>();
            }

            public ArrayList<String> getAccounts() {
                return accounts;
            }

            public void addAccount(String account) {
                accounts.add(account);
            }

            public void listSortedAccounts() {
                accounts.sort(String::compareTo);
                for (String account: accounts) {
                    System.out.print(account + " ");
                }
                System.out.println();
            }
        }

        class ListOfTransactions {
            private final ArrayList<Transaction> transactions;
            private final HashMap<String, ArrayList<Transaction>> accountHashMap;

            public ListOfTransactions() {
                this.accountHashMap = new HashMap<>();
                this.transactions = new ArrayList<>();
            }

            public ArrayList<Transaction> getTransactions() {
                return transactions;
            }

            public void addTransaction(Transaction transaction) {
                transactions.add(transaction);
                ArrayList<Transaction> accountTransaction = accountHashMap.getOrDefault(transaction.getFromAccount(), new ArrayList<>());
                accountTransaction.add(transaction);
                accountHashMap.put(transaction.getFromAccount(), accountTransaction);
            }

            public int numberTransactions() {
                return this.getTransactions().size();
            }

            public int totalMoneyTransaction() {
                int sum = 0;
                for (Transaction transaction: transactions) {
                    sum += transaction.getMoney();
                }
                return sum;
            }

            public int totalMoneyTransactionFrom(String id) {
                ArrayList<Transaction> accountTransaction = accountHashMap.get(id);
                int sum = 0;
                if (accountTransaction == null) {
                    return 0;
                }
                for (Transaction transaction: accountTransaction) {
                    sum += transaction.getMoney();
                }
                return sum;
            }

            public int getInspectCycle(String id, int k) {
                LinkedList<String> visited = new LinkedList<>();
                return inspectCycle(id, id, 0, k, visited);
            }

            private int inspectCycle(String account, String account1, int count, int k, LinkedList<String> visited) {
                int max = 0;
                ArrayList<Transaction> accountTransaction = accountHashMap.get(account);
                if (accountTransaction == null) {
                    return max;
                }
                for (Transaction transaction: accountTransaction) {
                    if (!visited.contains(transaction.getToAccount())) {
                        if (transaction.getToAccount().equals(account1)) {
                            if (count + 1 == k) {
                                max = 1;
                            } else {
                                continue;
                            }
                        }
                        if (max == 1) {
                            return 1;
                        }
                        visited.addLast(transaction.getToAccount());
                        max = inspectCycle(transaction.getToAccount(), account1, count + 1, k, visited);
                        visited.removeLast();
                    }
                }
                return max;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ListOfAccount listOfAccount = new ListOfAccount();
        ListOfTransactions listOfTransactions = new ListOfTransactions();
        StringBuilder output = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String fromAccount= st.nextToken();
            if (fromAccount.equals("#")) {
                break;
            }
            if (!listOfAccount.getAccounts().contains(fromAccount)) {
                listOfAccount.addAccount(fromAccount);
            }

            String toAccount = st.nextToken();
            if (!listOfAccount.getAccounts().contains(toAccount)) {
                listOfAccount.addAccount(toAccount);
            }
            int money = Integer.parseInt(st.nextToken());
            String time = st.nextToken();
            String atm = st.nextToken();

            listOfTransactions.addTransaction(new Transaction(fromAccount, toAccount, money));
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("#")) {
                break;
            }

            if (command.equals("?number_transactions")) {
                output.append(listOfTransactions.numberTransactions());
                output.append('\n');
            }

            if (command.equals("?total_money_transaction")) {
                output.append(listOfTransactions.totalMoneyTransaction());
                output.append('\n');
            }

            if (command.equals("?list_sorted_accounts")) {
                listOfAccount.listSortedAccounts();
            }

            if (command.equals("?total_money_transaction_from")) {
                String id = st.nextToken();
                output.append(listOfTransactions.totalMoneyTransactionFrom(id));
                output.append('\n');
            }

            if (command.equals("?inspect_cycle")) {
                String id = st.nextToken();
                int k = Integer.parseInt(st.nextToken());
                output.append(listOfTransactions.getInspectCycle(id, k));
                output.append('\n');
            }
        }
        System.out.println(output);
    }
}

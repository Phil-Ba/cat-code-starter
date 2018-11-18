package catcode;

import java.util.*;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
        solve1(IOUtils.readFileAsList("level1/level1-1.txt"),"level1/out1-1.txt");
        solve1(IOUtils.readFileAsList("level1/level1-2.txt"),"level1/out1-2.txt");
        solve1(IOUtils.readFileAsList("level1/level1-3.txt"),"level1/out1-3.txt");
        solve1(IOUtils.readFileAsList("level1/level1-4.txt"),"level1/out1-4.txt");
    }
    
    static class Account {
        int balance;
        String name;
        
        public Account(String name, int balance) {
            this.balance = balance;
            this.name = name;
        }
    }
    
    static class Transaction {
        String from, to;
        int amount;
        long timestamp;
        
        public Transaction(String from, String to, int amount, long timestamp) {
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.timestamp = timestamp;
        }
        
    }
    
    static void solve1(List<String> input, String outloc) {
        Iterator<String> iter = input.iterator();
        int accountsNumber = Integer.parseInt(iter.next());
        HashMap<String, Account> accounts = new HashMap<>();
        List<Account> accountsList = new ArrayList<>();
        for (int i = 0; i < accountsNumber; i++) {
            String[] line = iter.next().split(" ");
            Account acc = new Account(line[0], Integer.parseInt(line[1]));
            accounts.put(acc.name, acc);
            accountsList.add(acc);
        }
        
        int transactionNumber = Integer.parseInt(iter.next());
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < transactionNumber; i++) {
            String[] line = iter.next().split(" ");
            Transaction transaction = new Transaction(line[0], line[1], Integer.parseInt(line[2]), Long.parseLong(line[3]));
            transactions.add(transaction);
        }
        
        transactions.stream()
                .sorted(Comparator.comparingLong(t -> t.timestamp))
                .forEach(transaction -> {
                    Account from = accounts.get(transaction.from);
                    Account to = accounts.get(transaction.to);
                    from.balance -= transaction.amount;
                    to.balance += transaction.amount;
                });
        
        List<String> out = new ArrayList<>();
        out.add(""+accountsNumber);
        accountsList.forEach(a -> out.add(a.name + " " + a.balance));
        IOUtils.write(outloc,out);
    }
    
}

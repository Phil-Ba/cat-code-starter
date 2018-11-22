package catcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public class MainLevel2 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
        solve1(IOUtils.readFileAsList("level2/level2-eg.txt"), "level2/out2-eg.txt");
        solve1(IOUtils.readFileAsList("level2/level2-1.txt"), "level2/out2-1.txt");
        solve1(IOUtils.readFileAsList("level2/level2-2.txt"), "level2/out2-2.txt");
        solve1(IOUtils.readFileAsList("level2/level2-3.txt"), "level2/out2-3.txt");
        solve1(IOUtils.readFileAsList("level2/level2-4.txt"), "level2/out2-4.txt");
    }
    
    static class Account {
        int balance;
        int limit;
        String name;
        String accountNumber;
        
        public Account(String name, String accountNumber, int balance, int limit) {
            this.balance = balance;
            this.limit = limit;
            this.name = name;
            this.accountNumber = accountNumber;
        }
        
        boolean valid() {
            if (!accountNumber.substring(0, 3).equals("CAT")) {
                return false;
            }
            Map<Integer, Integer> charCount = accountNumber.substring(5, 15)
                    .chars()
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), i -> 1, (i, j) -> i + j));
            boolean valid = charCount.entrySet()
                    .stream()
                    .allMatch(entry -> {
                                if (entry.getKey() >= 97) {
                                    return entry.getValue().equals(charCount.get(entry.getKey() - 32));
                                } else {
                                    return entry.getValue().equals(charCount.get(entry.getKey() + 32));
                                }
                            }
                    );
            if (!valid) {
                return false;
            }
            
            int sum = charCount.entrySet()
                    .stream()
                    .map(e -> e.getValue() * e.getKey())
                    .mapToInt(integer -> integer)
                    .sum();
            int fix = "CAT00".chars()
                    .sum();
            int chksm = (sum + fix) % 97;
            chksm = 98 - chksm;
            int chksm2 = Integer.parseInt(accountNumber.substring(3, 5));
            
            return chksm == chksm2;
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
            Account acc = new Account(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            accountsList.add(acc);
        }
        accountsList = accountsList.stream()
                .filter(Account::valid)
                .peek(account -> accounts.put(account.accountNumber, account))
                .collect(Collectors.toList());
        
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
                    if (from == null || to == null) {
                        return;
                    }
                    if (from.balance - transaction.amount < from.limit * -1) {
                        return;
                    }
                    from.balance -= transaction.amount;
                    to.balance += transaction.amount;
                });
        
        List<String> out = new ArrayList<>();
        out.add("" + accounts.size());
        accountsList.forEach(a -> out.add(a.name + " " + a.balance));
        IOUtils.write(outloc, out);
    }
    
}

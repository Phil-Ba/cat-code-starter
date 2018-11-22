package catcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class MainLevel3 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
//        solve1(IOUtils.readFileAsList("level3/level3-eg.txt"), "level3/out3-eg.txt");
//        solve1(IOUtils.readFileAsList("level3/level3-1.txt"), "level3/out3-1.txt");
//        solve1(IOUtils.readFileAsList("level3/level3-2.txt"), "level3/out3-2.txt");
//        solve1(IOUtils.readFileAsList("level3/level3-3.txt"), "level3/out3-3.txt");
        solve1(IOUtils.readFileAsList("level3/level3-4.txt"), "level3/out3-4.txt");
    }
    
    
    static class Transaction implements Comparable<Transaction> {
        String id;
        List<Input> inputs = new ArrayList<>();
        List<Output> outputs = new ArrayList<>();
        long timestamp;
        
        public Transaction(String id, long timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
        
        boolean isValid(List<Transaction> previousValids) {
//            if (inputs.size() != outputs.size()) {
//                return false;
//            }
            //amounts > 0
            if (inputs.stream().mapToInt(o -> o.amount).anyMatch(i -> i < 1)) {
                return false;
            }
            if (outputs.stream().mapToInt(o -> o.amount).anyMatch(i -> i < 1)) {
                return false;
            }
            //in amount==out amount
            IntStream inAmounts = outputs.stream().mapToInt(o -> o.amount);
            IntStream outAmounts = inputs.stream().mapToInt(i -> i.amount);
            if (inAmounts.sum() != outAmounts.sum()) {
                return false;
            }
            //duplicate owners
            if (outputs.stream().map(o -> o.owner)
                    .collect(Collectors.toMap(Function.identity(), i -> 1, (u, u2) -> u + u2))
                    .values()
                    .stream()
                    .anyMatch(i -> i > 2)
            ) {
                return false;
            }

            if(previousValids.isEmpty()){
                return true;
            }
//            all valid inputs
            List<Output> usedOuts = new ArrayList<>();
            for (Input input : inputs) {
                if(input.owner.equals("origin")){
                    continue;
                }
                Optional<Output> sourceOutput = previousValids.stream()
                        .flatMap(p -> p.outputs.stream().filter(o -> !o.used))
                        .filter(o -> o.owner.equals(input.owner) && input.amount == o.amount)
                        .findFirst();
                if (!sourceOutput.isPresent()) {
                    return false;
                }
                Output s = sourceOutput.get();
                if(usedOuts.contains(s)){
                    return false;
                }
                usedOuts.add(s);
                
            }
            usedOuts.forEach(o->o.used=true);
            return true;
        }
    
        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(" ")
                    .add(id)
                    .add(inputs.size()+"");
            inputs.forEach(i->joiner.add(i.toString()));
            joiner.add(outputs.size() + "");
            outputs.forEach(i->joiner.add(i.toString()));
            joiner.add(timestamp + "");
            return joiner.toString();
        }
    
        @Override
        public int compareTo(Transaction transaction) {
            return Long.compare(timestamp, transaction.timestamp);
        }
    }
    
    static class Input {
        String id;
        String owner;
        int amount;
        
        public Input(String id, String owner, int amount) {
            this.id = id;
            this.owner = owner;
            this.amount = amount;
        }
    
        @Override
        public String toString() {
            return id + " " + owner + " " + amount;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Input)) {
                return false;
            }
            Input input = (Input) o;
            return Objects.equals(id, input.id);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
    
    static class Output {
        String owner;
        int amount;
        boolean used = false;
        
        public Output(String owner, int amount) {
            this.owner = owner;
            this.amount = amount;
        }
        @Override
        public String toString() {
            return owner + " " + amount;
        }
        
    }
    
    static void solve1(List<String> input, String outloc) {
        Iterator<String> iter = input.iterator();
        int transactionNumber = Integer.parseInt(iter.next());
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < transactionNumber; i++) {
            String[] line = iter.next().split(" ");
            int ins = Integer.parseInt(line[1]);
            int outsStart = ins * 3 + 2;
            int outs = Integer.parseInt(line[outsStart]);
            long timestamp = Long.parseLong(line[line.length - 1]);
            Transaction transaction = new Transaction(line[0], timestamp);
            for (int j = 2; j < ins * 3; ) {
                transaction.inputs.add(new Input(line[j++], line[j++], Integer.parseInt(line[j++])));
            }
            for (int j = outsStart + 1; j < outsStart +outs * 2; ) {
                transaction.outputs.add(new Output(line[j++], Integer.parseInt(line[j++])));
            }
            transactionList.add(transaction);
        }
        Collections.sort(transactionList);
        List<Transaction> valids=new ArrayList<>();
        for (Transaction transaction : transactionList) {
            boolean valid = transaction.isValid(valids);
            if (valid){
                valids.add(transaction);
            }
        }
        List<String> out = new ArrayList<>();
        Collections.sort(valids);
        out.add("" + valids.size());
        valids.forEach(t -> out.add(t.toString()));
        IOUtils.write(outloc, out);
    }
    
}

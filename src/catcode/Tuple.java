package catcode;

import java.util.Objects;

public class Tuple<L,R> {
    L l;
    R r;
    
    public Tuple(L l, R r) {
        this.l = l;
        this.r = r;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple)) {
            return false;
        }
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(l, tuple.l) &&
                Objects.equals(r, tuple.r);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(l, r);
    }
}


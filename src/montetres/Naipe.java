package montetres;

/**
 * Created by joao on 19/03/17.
 */
public enum Naipe {
    CLUBS {
        @Override
        public String toString() {
            return "C";
        }
    }, HEARTS {
        @Override
        public String toString() {
            return "H";
        }
    }, DIAMONDS {
        @Override
        public String toString() {
            return "D";
        }
    }, SPADES {
        @Override
        public String toString() {
            return "S";
        }
    };

}

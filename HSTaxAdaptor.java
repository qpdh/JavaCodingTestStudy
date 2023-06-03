import java.util.ArrayList;
import java.util.List;

public class HSTaxAdaptor {

    interface Subject {
        public void registerObserver(Observer o);

        public void removeObserver(Observer o);

        public void notifyObservers();
    }

    interface Observer {
        public void update(Subject sub);
    }

    class Table implements Observer {
        private int a, b, c;
        private Subject sub;

        public Table(Subject sub) {
            this.sub = sub;
            sub.registerObserver(this);
        }

        public void update(Subject sub) {
            if (sub instanceof EData) {
                EData eSub = (EData) sub;
                this.a = eSub.getA();
                this.b = eSub.getB();
                this.c = eSub.getC();
            }
            display();
        }

        public void display() {

        }
    }

    class EData implements Subject {
        private List<Observer> observers;
        private int a, b, c;

        public EData() {
            observers = new ArrayList<>();
        }

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            if (!observers.isEmpty()) {
                observers.remove(observers.indexOf(o));
            }
        }

        @Override
        public void notifyObservers() {
            for (int i = 0; i < observers.size(); i++) {
                Observer observer = observers.get(i);
                observer.update(this);
            }
        }

        public void changed(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            notifyObservers();
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }

    class ETest {
        public static void main(String[] args) {
            EData eData = new EData();
            eData.changed(10, 40, 50);
        }
    }

}

package rx.ee.bean;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CDISingleton {
    private int counter = 0;

    public void increment() {
        counter++;
        System.out.println("counter is currently " + counter);
    }

    public int getCounter() {
        return counter;
    }
}
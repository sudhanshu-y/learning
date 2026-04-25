public class BankAccountSynchronized{

    private double balance = 1000;

    public synchronized void deposit(double amount){
        this.balance+=amount;
        System.out.println(Thread.currentThread().getName()+ " deposit = "+ amount + "  balance = " + this.balance);
    }

    public synchronized void withdraw(double amount){
        if(amount>this.balance){
            System.out.println(Thread.currentThread().getName()+ " FAILED!: "+" withdraw = "+ amount + "  balance = " + this.balance);            
            return;
        }
        this.balance-=amount;
        System.out.println(Thread.currentThread().getName()+ " withdraw = "+ amount + "  balance = " + this.balance);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ " - Started");

        BankAccountSynchronized account = new BankAccountSynchronized();

        Thread t1 = new Thread(() -> {
            for(int i=0;i<10;i++){
                account.deposit(10);
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        );

        Thread t2 = new Thread(() -> {
            for(int i=0;i<10;i++){
                account.withdraw(10);
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        );

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final balance = "+ account.balance);
        System.out.println(Thread.currentThread().getName()+ " - Completed");
    }
}
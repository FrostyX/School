package zp3jv;

public class Main {
	
	public static volatile boolean done = false;

	public static void main(String[] args) {
		Thread fibThread = new Thread() {
			public void run() {
				System.out.println("\n43th number is " + fib(43));
				done = true;
			}
		};
		fibThread.start();
		
		long startTime = System.currentTimeMillis();
		System.out.print("Elapsed time: ");
		while (!done) {
			System.out.print(((System.currentTimeMillis() - startTime) / 1000) + "s ... ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int fib(int n) {
		return n < 2 ? n : fib(n - 1) + fib(n - 2);
	}
}

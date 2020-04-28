package Concurrency;

// IF a thread is accessing the Same data as another one is writing
// and the updating of the data is then notified to the other one

// In this example when the Producer finds that the buffer is full
// It puts the thread in the waiting state and notifies the other
// This makes the consumer to get the lock and it gets notified
// the consumer consumes until the object is empty .
// If the object is empty then consumer goes into wait state and
// notifies producer.

class WaitNotifyExample
{

	static final int BUFFER_SIZE = 10;
	static int count = 0;
	static int[] buffer = new int[BUFFER_SIZE];

	static boolean isFull()
	{
		System.out.println("Is full called with count " + count);
		return count == BUFFER_SIZE;
	}

	static boolean isEmpty()
	{
		System.out.println("Is empty called with count " + count);
		return count == 0;
	}

	//Step 1
	private static final Object lock = new Object();

	static class Producer
	{
		void produce()
		{
			//Step1
			synchronized(lock)
			{
				if(isFull())
				{
					//Step 2
					try
					{
						lock.wait();
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				//Step 2
				lock.notifyAll();
			}
		}
	}

	static class Consumer
	{

		void consume()
		{
			//Step 1
			synchronized(lock)
			{
				if(isEmpty())
				{
					//Step2
					try
					{
						lock.wait();
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}

				}
				buffer[--count] = 0;
				lock.notifyAll();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		Producer producer = new Producer();
		Consumer consumer = new Consumer();

		Runnable produceTask = () -> {
			for(int i = 0; i < 50; i++)
			{
				System.out.println("Producing = " + i);
				producer.produce();
			}
			System.out.println("Done producing");
		};
		Runnable consumeTask = () -> {
			for(int i = 0; i < 45; i++)
			{
				System.out.println("Consuming = " + i);
				consumer.consume();
			}
			System.out.println("Done consuming");
		};

		Thread consumerThread = new Thread(consumeTask);
		Thread producerThread = new Thread(produceTask);

		consumerThread.start();
		producerThread.start();

		consumerThread.join();
		producerThread.join();

		System.out.println("Data in the buffer: " + count);
	}

}

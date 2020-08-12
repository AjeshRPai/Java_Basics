package Streams;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class CollectorsExample
{
	public static void main(String[] args)
	{
		Map<Boolean, List<Account>> partByBalance = getAccounts().stream().collect(Collectors.partitioningBy(a -> a.getBalance() >= 10000));

		System.out.println("partByBalance = " + partByBalance);

		Map<AccountState, List<Account>> groupingByState = getAccounts().stream().collect(Collectors.groupingBy(Account::getState));

		System.out.println("groupingByState = " + groupingByState);

		long summary = getAccounts().stream().collect(summingLong(Account::getBalance));

		System.out.println("summary = " + summary);

		double average = getAccounts().stream().collect(averagingLong(Account::getBalance));

		System.out.println("average = " + average);

		Map<AccountState, Long> sumByStates = getAccounts().stream().collect(Collectors.groupingBy(Account::getState, summingLong(Account::getBalance)));

		System.out.println("sumByStates = " + sumByStates);

		String customCollector = getAccounts().stream().map(account -> account.balance.toString()).collect(joinectorFunctionalStyle(","));

		System.out.println("customCollector = " + customCollector);

	}

	/**
	 * REFERENCE
	 * https://medium.com/better-programming/java-stream-collectors-explained-6209a67a4c29
	 */
	static Collector<CharSequence, StringJoiner, String> joinectorFunctionalStyle(CharSequence delimiter)
	{
		return Collector.of(() -> new StringJoiner(delimiter), // supplier
			StringJoiner::add,                 // accumulator
			StringJoiner::merge,               // combiner
			StringJoiner::toString);           // finisher
	}

	private static ArrayList<Account> getAccounts()
	{
		ArrayList<Account> accounts = new ArrayList<>();

		accounts.add(new Account(100L, AccountState.ACTIVE, 11414141L));
		accounts.add(new Account(121000L, AccountState.BLOCKED, 11414142L));
		accounts.add(new Account(131000L, AccountState.REMOVED, 11414143L));
	/*	accounts.add(new Account(141000L,AccountState.ACTIVE,11414144L));
		accounts.add(new Account(151000L,AccountState.REMOVED,11414145L));
		accounts.add(new Account(161000L,AccountState.BLOCKED,11414146L));
		accounts.add(new Account(171000L,AccountState.ACTIVE,11414147L));
		accounts.add(new Account(181000L,AccountState.REMOVED,11414148L));
		accounts.add(new Account(191000L,AccountState.BLOCKED,11414149L));
		accounts.add(new Account(201000L,AccountState.ACTIVE,11414150L));
		accounts.add(new Account(211000L,AccountState.REMOVED,11414151L));
		accounts.add(new Account(221000L,AccountState.BLOCKED,11414152L));*/

		return accounts;

	}

	enum AccountState
	{
		BLOCKED,
		ACTIVE,
		REMOVED
	}

	static class Account
	{
		Long balance;
		AccountState state;
		Long number;

		public Account(
			Long balance, AccountState state, Long number)
		{
			this.balance = balance;
			this.state = state;
			this.number = number;
		}

		public Long getBalance()
		{
			return balance;
		}

		public AccountState getState()
		{
			return state;
		}

		public Long getNumber()
		{
			return number;
		}

		@Override
		public String toString()
		{
			return "Account{" + "balance=" + balance + ", state=" + state + ", number=" + number + '}';
		}
	}
}

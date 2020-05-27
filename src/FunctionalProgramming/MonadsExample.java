package FunctionalProgramming;

import java.util.Optional;
import java.util.UUID;

/**
 * This exercise is to understand the use of monads
 * Here the objective is to find the bank name of the user
 * if the user is not null has an account and when the bank has a name
 */

public class MonadsExample
{

	/**
	 * first way . Without using the any monads
	 *
	 * In this way the function is lengthy and not readable
	 */

	public static String getBankNameWhereUserHasAccountWithoutUsingMonad(User user)
	{
		final String error = "<No bank found>";

		if(user == null)
		{
			return error;
		}

		final Account account = user.getAccount();
		if(account == null)
		{
			return error;
		}

		final Bank bank = account.getBank();
		if(account.getBank() == null)
		{
			return error;
		}

		if(bank.getName() == null)
		{
			return error;
		}

		return bank.getName();
	}

	/**
	 * In the second way the Required fields are wrapped with optional value so that they can be treated with monads
	 * This would require data underlying data modification which is not always possible
	 *
	 * User (firstName: String, lastName: String, account: Optional<Account>)
	 * Account (guid: UUID, bank: Optional<Bank>, balance: Long)
	 * Bank (uniqueNumber: String, name: Optional<String>)
	 */

	public static String getBankNameWhereUserHasAccountWithDataModification(User user)
	{
		return Optional.ofNullable(user).flatMap(User::getAccount).flatMap(Account::getBank).flatMap(Bank::getName).orElse("<No bank found>");
	}

	/**
	 * The third way is also very concise and readable and does not demand to change a domain model.
	 * It's often preferred way. The method map is very useful in practice but not forget about flatMap as well.
	 *
	 * we consider map as an additional method for a base monad concept,
	 * because it may be combined using the base monad methods.
	 */

	public static String getBankNameWhereUserHasAccountWithoutDataModification(User user)
	{
		return Optional.ofNullable(user).map(User::getAccount).map(Account::getBank).map(Bank::getName).orElse("<No bank found>");
	}

	class User
	{
		String firstName;
		String lastName;
		Account account;

		public Account getAccount()
		{
			return account;
		}
	}

	class Account
	{
		UUID guid;
		Bank bank;
		Long balance;

		public UUID getGuid()
		{
			return guid;
		}

		public Bank getBank()
		{
			return bank;
		}

		public Long getBalance()
		{
			return balance;
		}
	}

	class Bank
	{
		String name;
		String bankNumber;

		public String getName()
		{
			return name;
		}

		public String getBankNumber()
		{
			return bankNumber;
		}
	}

}

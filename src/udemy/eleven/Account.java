package udemy.eleven;

public class Account {

	int amount;
	String accName;
	public Account(int bal, String name) {
		this.amount = bal;
		accName = name;
	}
	
	public String getAccName(){
		return accName;
	}
	
	public void deposit(int am){
		amount += am;
	}
	
	public void withdraw(int am){
		amount -= am;
	}
	
	public int balance(){
		return amount;
	}
	
	public static void transfer(Account acc1, Account acc2, int amount){
		
		//System.out.println("Transfering "+amount+" from Account "+acc1.getAccName()+" to "+acc2.getAccName());
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
}

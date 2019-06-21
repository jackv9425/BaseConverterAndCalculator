package calculator;
import java.util.ArrayList;
import java.util.Scanner;
public class MainA {
	public static String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("What would you like to do?");
		switch(console.nextLine()) {
		case "Calculate" : // Calculate in a given base
			System.out.println("Enter The Base (In Decimal Notation) You Would \nLike To Calculate In (Maximum 62): ");
			int base = console.nextInt();
			System.out.println("Enter your calculation with an\n\"equals sign\" on the end:");
			ArrayList<Number> list = new ArrayList<Number>();
			ArrayList<String> operators = new ArrayList<String>();
			String num;
			boolean isEquals = false;
			while(isEquals == false) {
				try {
					num = Integer.toString(console.nextInt());
				} catch (Exception e0) { // prevents java.util.InputMismatchException
						num = console.next();
				}
				list.add(new Number(base, num));
				num = console.next();
				if(num.compareTo("=") == 0)
					isEquals = true;
				else {
					operators.add(num);
				}
			}
			System.out.println(calculate(list, operators, base).getNum());
			break;
		case "Base Conversion" :
			System.out.println("Input Original Base And New Base (In Decimal Notation)");
			int oldBase = console.nextInt();
			int newBase = console.nextInt();
			System.out.println("Input The Number You Want To Convert");
			Number a = new Number(oldBase, console.next());
			System.out.println("Your New Number Is: " + convert(a.getInt(), newBase));
			break;
		default :
			System.out.println("Invalid Input. Program Terminated.");
			break;
		}
		console.close();
	}
	public static Number calculate(ArrayList<Number> list, ArrayList<String> operators, int base) {
		for(int i = 0; i < operators.size(); i++)
			if(operators.get(i).compareTo("*") == 0) { // multiplication
				operators.remove(i);
				int product = list.get(i).getInt() * list.remove(i+1).getInt();
				list.set(i, new Number(10, "" + product));
				i--;
			}
			else if(operators.get(i).compareTo("/") == 0) { // division
				operators.remove(i);
				int product = list.get(i).getInt() / list.remove(i+1).getInt();
				list.set(i, new Number(10, "" + product));
				i--;
			}
		for(int i = 0; i < operators.size(); i++) {
			if(operators.get(i).compareTo("+") == 0) { // addition
				operators.remove(i);
				int sum = list.get(i).getInt() + list.remove(i+1).getInt();
				list.set(i, new Number(10, "" + sum));
				i--;
			}
			else if(operators.get(i).compareTo("-") == 0) { // subtraction
				operators.remove(i); 
				int difference = list.get(i).getInt() - list.remove(i+1).getInt();
				list.set(i, new Number(10, "" + difference));
				i--;
			}
		}
		return new Number(base, convert(list.get(0).getInt(), base));
	}
	public static String convert(int number, int base) { // convert from base 10 to base x
		int quotient = number / base;
		int remainder = number % base;
		if (quotient == 0)
			return "" + charList.charAt(remainder);
		else
			return convert(quotient, base) + charList.charAt(remainder);         
	}
}
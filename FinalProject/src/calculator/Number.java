package calculator;
public class Number {
	private String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private int nNum = 0;
	private int base;
	private String sNum;
	public Number(int base, String num) {
		this.base = base;
		this.chars = chars.substring(0, base);
		this.sNum = num;
		for(int i = sNum.length() - 1, j = 0; i >= 0; i--, j++) { // create and int that stores the number's value in base 10
			this.nNum += Math.pow(base, i) * chars.indexOf(sNum.charAt(j));
		}
	}
	public int getInt() {
		return nNum;
	}
	public int getBase() {
		return base;
	}
	public String getNum() {
		return sNum;
	}
}
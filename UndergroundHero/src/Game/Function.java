package Game;

import java.util.concurrent.TimeUnit;

public class Function {
	
	public void printBox(String... strings) {
	    int maxBoxWidth = getMaxLength(strings);
	    String line = "+" + fill('-', maxBoxWidth + 2) + "+";
	    System.out.println(line);
	    for (String str : strings) {
	        System.out.printf("| %s |%n", padString(str, maxBoxWidth));
	    }
	    System.out.println(line);
	}

	private int getMaxLength(String... strings) {
	    int len = Integer.MIN_VALUE;
	    for (String str : strings) {
	        len = Math.max(str.length(), len);
	    }
	    return len;
	}
	
	private String padString(String str, int len) {
	    StringBuilder sb = new StringBuilder(str);
	    return sb.append(fill(' ', len - str.length())).toString();
	}
	
	private String fill(char ch, int len) {
	    StringBuilder sb = new StringBuilder(len);
	    for (int i = 0; i < len; i++) {
	        sb.append(ch);
	    }
	    return sb.toString();
	}
	
	public void delay(int delay) {
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String string, long delay) {
		try {
			for (char ch : string.toCharArray()) {
				System.out.print(ch);
				TimeUnit.MILLISECONDS.sleep(delay);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: print()");
		}

	}
	
}

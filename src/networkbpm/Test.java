package networkbpm;

import java.awt.Toolkit;
import java.io.Console;
import java.util.Scanner;

public class Test {
	public static String res = "";
	public static int sleep = 3000;
	public static void main(String[] args) {
		Thread beepador = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						if(res.equals("ha internet")) {
							sleep = 3000;
							Thread.sleep(sleep);
							//Toolkit.getDefaultToolkit().beep();
							System.out.println(res);
						}else {
							sleep = 500;
							Thread.sleep(sleep);
							Toolkit.getDefaultToolkit().beep();
							System.out.println(res);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		Thread verificador = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(sleep);
						Scanner scan = new Scanner(Runtime.getRuntime().exec("ping 8.8.8.8").getInputStream());
						String text = scan.nextLine();
						text = scan.nextLine();
						text = scan.nextLine();
						if(text.charAt(0) == 'R') {
							int index = 0;
							for(int i = 0;i<text.length();i++) {
								if(text.charAt(i) == ':') {
									index = i;
									break;
								}
							}
							if(text.charAt(index+2) == 'b') {
								res = "ha internet";
							}else {
								res = "nao ha internet";
							}
							
						}else {
							res = "nao ha internet";
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		verificador.start();
		beepador.start();
	}
}

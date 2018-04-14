package tbs.server;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class CLI {
	public static void main(String...args) throws Exception {

		NumberFormat nf = new DecimalFormat("$0");
		int x = nf.parse("$3").intValue();
		System.out.println(x);

		TBSServerImpl server = new TBSServerImpl();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("Call method named: ");
			String methodName = scanner.nextLine().trim();
			for(Method method : server.getClass().getMethods()) {
				if(method.getName().equalsIgnoreCase(methodName)) {
					Object[] parameterValues = new Object[method.getParameterTypes().length];
					int i = 0;
					for(Class<?> parameterType : method.getParameterTypes()) {
						System.out.print(i + " (" + parameterType.getSimpleName() + "): ");
						String input = scanner.nextLine().trim();
						
						// switch on the types
						if(parameterType.equals(int.class)) {
							parameterValues[i] = Integer.parseInt(input);
						} else {
							parameterValues[i] = input;
						}
						
						++i;
					}
					
					Object result = method.invoke(server, parameterValues);
					
					if(result instanceof List) {
						int j = 0;
						
						@SuppressWarnings("rawtypes")
						List list = (List) result;

						for(Object res : list) {
							System.out.println("[" + j++ + "] " + res);
						}
					} else {
						System.out.println(result.toString());
					}
					
					break;
				}
				
				server.dump();
			}
		}
	}
}

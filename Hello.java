package lab01;

public class Hello {

	public Hello() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Worlds");
		if(args.length>0){
			for(int i =0; i<Integer.parseInt(args[0]);i++){
				System.out.println(i);
			}
		}
	}

}

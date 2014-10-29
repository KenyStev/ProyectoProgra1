import java.util.Scanner;

public class Proyecto1{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String[] nameProd = {"Azucar","Avena","Trigo","Maiz"};
		double[] precioProd = {30,25,32,20};
		int[] kgPorProd = new int[4];
		String[] logs = new String[10];
		double[] descuentos = {0,0.05,0.1};
		int[] compYVent = new int[2];
		int[] starProd = new int[4];

		double caja = 0.0;
		String nameCliente, nameProv, opt;
		int codeProd, kgCOV, optMenu, indiceDescu = 0, indiceLog = 0;
		double subTotal, total, isv, precioCompra, banco = 0.0;
		boolean isFirstOpen = true, isOpen = false;	

		do{
			System.out.println("1. Abrir Caja");
			System.out.println("2. Ventas");
			System.out.println("3. Compras");
			System.out.println("4. Estadistica");
			System.out.println("5. Ver Inventario");
			System.out.println("6. Cierre");
			System.out.println("7. Logs");
			System.out.println("8. Salir del Sistema\n");

			System.out.print("Escoja su Opcion: ");
			optMenu = scan.nextInt();

			switch(optMenu){
				case 1:	//Abrir caja
					if(isFirstOpen){
						System.out.print("Ingrese la cantidad de efectivo a depositar en caja: ");
						double depositar = scan.nextDouble();
						caja += depositar;
						if(indiceLog<10){
							logs[indiceLog++] = "Se abrió la caja y se deposito Lps. " + depositar;
						}else{
							for(int i = 1; i<logs.length;i++){
								logs[i-1] = logs[i];
							}
							logs[indiceLog-1] = "Se abrió la caja y se deposito Lps. " + depositar;
						}
						
					}
					isOpen=true;
				break;
				case 2:	//Ventas

				break;
				case 3:	//Compras

				break;
				case 4: //Estadisticas

				break;
				case 5: //Ver Inventario

				break;
				case 6: //Cierre

				break;
				case 7: //Logs
					for(int i=indiceLog-1;i>=0;i--)
						System.out.println(logs[i]);
				break;
				case 8: //Salir del sistema

				break;
				default:
				System.out.println("Opcion no existe!\n");
			}

		}while(optMenu!=8);

	}
}
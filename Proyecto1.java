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
		int codeProd, kgCOV, optMenu, indiceDescu = 0;
		double subTotal, total, isv, precioCompra, banco = 0.0;
		boolean isFirstOpen = true;	

		do{
			System.out.print("1. Abrir Caja");
			System.out.print("2. Ventas");
			System.out.print("3. Compras");
			System.out.print("4. Estadistica");
			System.out.print("5. Ver Inventario");
			System.out.print("6. Cierre");
			System.out.print("7. Logs");
			System.out.print("8. Salir del Sistema\n");

			System.out.print("Escoja su Opcion: ");
			optMenu = scan.next();

			switch(optMenu){
				case 1: break;
				case 2: break;
				case 3: break;
				case 4: break;
				case 5: break;
				case 6: break;
				case 7: break;
				case 8: break;
				default:
			}

		}while(optMenu!=8);

	}
}
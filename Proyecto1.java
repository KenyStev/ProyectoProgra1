import java.util.Scanner;

public class Proyecto1{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String[] nameProd = {"Azucar","Avena","Trigo","Maiz"}, logs = new String[10];
		double[] precioProd = {30,25,32,20}, descuentos = {0,0.05,0.1}, ingresoYGasto = {0.0, 0.0};
		int[] kgPorProd = {0, 0, 0, 0}, compYVent = {0, 0}, starProd = {0, 0, 0, 0};

		double caja = 0.0, subTotal=0, total, isv, descuent=0, precioCompra, banco = 0.0, depositoBanc=0;
		String nameCliente, nameProv, opt, msj;
		int codeProd, kgCOV, optMenu, indiceDescu = 0, indiceLog = 0 , I=0, G=1;
		boolean isFirstOpen = true, isOpen = false;	

		do{
			System.out.println("***** MENU DEL SISTEMA *****\n");
			System.out.println("\t1. Abrir Caja");
			System.out.println("\t2. Ventas");
			System.out.println("\t3. Compras");
			System.out.println("\t4. Estadistica");
			System.out.println("\t5. Ver Inventario");
			System.out.println("\t6. Cierre");
			System.out.println("\t7. Logs");
			System.out.println("\t8. Salir del Sistema\n");

			System.out.print("Escoja su Opcion: ");
			optMenu = scan.nextInt();

			switch(optMenu){
				case 1:	//Abrir caja
					if(isFirstOpen){
						System.out.print("Ingrese la cantidad de efectivo a depositar en caja: ");
						double depositar = scan.nextDouble();
						caja += depositar;
						//Los logs solo van si la caja se abre por primera vez?
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
					if(isOpen){
						System.out.print("Ingrese nombre del Cliente: ");
						nameCliente = scan.next();
						msj = nameCliente + " ha comprado ";
						do{
							System.out.print("Ingrese el codigo del producto: ");
							codeProd = scan.nextInt();
							System.out.println("Producto: "+nameProd[--codeProd]+" Precio: "+precioProd[codeProd]);
							System.out.print("Ingrese cantidad de Kg: ");
							kgCOV = scan.nextInt();
							if(kgPorProd[codeProd]>=kgCOV){
								kgPorProd[codeProd] -= kgCOV;
								msj += kgCOV+" kgs del producto "+nameProd[codeProd]+",";
								subTotal += precioProd[codeProd]*kgCOV;
								starProd[codeProd]++;
							}else{
								System.out.println("No hay esa cantidad disponible, solo se cuenta con: "+kgPorProd[codeProd]);
							}

							System.out.print("Desea comprar otro producto?: ");
							opt = scan.next();
						}while(opt.equalsIgnoreCase("SI"));

						if(subTotal>0){
							if(subTotal>=5000){
								indiceDescu=2;
							}else if(subTotal>=1000){
								indiceDescu=1;
							}
							isv = subTotal * 0.15;
							descuent = subTotal*descuentos[indiceDescu];
							total = (subTotal-descuent)+isv;

							compYVent[I]++;
							ingresoYGasto[I] += subTotal; //El 15% no es parte de la ganancia?
							caja += total;
							msj += " dejando un total de Lps. "+total+" en caja";

							if(indiceLog<10){
								logs[indiceLog++] = msj;
							}else{
								for(int i=1; i<logs.length; i++){
									logs[i-1] = logs[i];
								}
								logs[indiceLog-1] = msj;
							}

							System.out.println("\n\t*\tSubtotal: "+subTotal);
							System.out.println("\t*\tDescuento: "+descuent);
							System.out.println("\t*\tImpuesto: "+isv);
							System.out.println("\t*\tTotal: "+total+"\n");

							subTotal=0;
							indiceDescu=0;

						}
					}else{
						System.out.println("\nLa caja no esta abierta!\n");
					}
				break;
				case 3:	//Compras
					if(isOpen){
						System.out.print("Ingrese el nombre del proveedor: ");
						nameProv=scan.next();
						System.out.print("Ingrese el codigo del producto: ");
						codeProd=scan.nextInt();
						System.out.print("Ingrese el precio de compra: ");
						precioCompra=scan.nextDouble();
						System.out.print("Ingrese la cantida en Kg a comprar del producto: ");
						kgCOV=scan.nextInt();

						double sub = precioCompra*kgCOV; //El precio de compra es por kg

						if (caja>sub) {
							System.out.println("Total a pagar: Lps. "+sub);
							caja -= sub;
							kgPorProd[--codeProd] += kgCOV;

							compYVent[G]++;
							ingresoYGasto[G] += sub;

							if(indiceLog<10){
								logs[indiceLog++] = "Se ha comprado a "+nameProv+" "+kgCOV+"kgs del producto "
								+nameProd[codeProd]+','+" con un gasto incurrido de Lps. "+sub;
							}else{
								for(int i = 1; i<logs.length;i++){
									logs[i-1] = logs[i];
								}
								logs[indiceLog-1] = "Se ha comprado a "+nameProv+" "+kgCOV+"kgs del producto "
								+nameProd[codeProd]+','+" con un gasto incurrido de Lps "+sub;
							}	
						}
						else{
							System.out.println("No Se Puede Pagar Compra!");
						}

					}else{
						System.out.println("\nLa caja no esta abierta!\n");
					}

				break;
				case 4: //Estadisticas

				break;
				case 5: //Ver Inventario

				break;
				case 6: //Cierre
					if(isOpen){
						System.out.println("Dinero en Caja: "+caja); //Ganancia o cantidad en caja?
						do{
							System.out.print("Cuanto Dinero desea depositar en el banco?: ");
							depositoBanc = scan.nextDouble();
							if(depositoBanc>caja*0.6){
								System.out.println("La cantidad a depositar es mayor al 60% de la caja!");
							}else{
								System.out.println("Depositado Lps. "+depositoBanc+" en el Banco");
							}
						}while(depositoBanc>caja*0.6);

						//Limpia contadores de compras y ventas del dia
						for(int i=0;i<compYVent.length;i++){
							compYVent[i]=0;
						}
						//Limpia acumulado de ingresos y gastos del dia
						for(int i=0;i<ingresoYGasto.length;i++){
							ingresoYGasto[i]=0;
						}
						//Limpia acumulador de producto estrella
						for(int i=0;i<starProd.length;i++){
							starProd[i]=0;
						}

						//caja+depositoBanc o lo que quedo en caja luego de hacer el deposito?
						if(indiceLog<10){
							logs[indiceLog++] = "Se cerro el dia con Lps. "+caja
							+" en Caja y se ha decidido depositar Lps. "+depositoBanc+" en el banco";
						}else{
							for(int i=1; i<logs.length;i++){
								logs[i-1]=logs[i];
							}
							logs[indiceLog-1] = "Se cerro el dia con Lps. "+caja
							+" en Caja y se ha decidido depositar Lps. "+depositoBanc+" en el banco";
						}
						banco += depositoBanc;
						caja -= depositoBanc;
						isOpen=false;
						isFirstOpen=false;
					}else{
						System.out.println("\nLa caja esta cerrada!\n");
					}
				break;
				case 7: //Logs
						System.out.println();
						for(int c=1, i=indiceLog-1;i>=0;i--, c++){
							System.out.println("\t"+c+". "+logs[i]);
						}
						
						if(indiceLog==0){
							System.out.println("-----No hay Logs disponibles!-----");
						}
						System.out.println();
				break;
				case 8: /*Salir del sistema*/ break;
				default:
				System.out.println("\nOpcion no existe!\n");
			}

		}while(optMenu!=8);

	}
}
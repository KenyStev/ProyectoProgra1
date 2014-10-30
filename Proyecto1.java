import java.util.Scanner;

public class Proyecto1{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String[] nameProd = {"Azucar","Avena","Trigo","Maiz"}, logs = new String[10];
		double[] precioProd = {30,25,32,20}, descuentos = {0,0.05,0.1}, ingresoYGasto = {0.0, 0.0};
		int[] kgPorProd = {100, 100, 100, 100}, compYVent = {0, 0}, starProd = {0, 0, 0, 0};

		double caja = 0.0;
		String nameCliente, nameProv, opt, msj="";
		int codeProd, kgCOV, optMenu, indiceDescu = 0, indiceLog = 0 , I=0, G=1;
		double subTotal=0, total, isv=0.15, descuent=0, precioCompra, banco = 0.0;
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
							ingresoYGasto[I] += subTotal; //El 15% no es parte de la ganancia
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

						}
					}else{
						System.out.println("La caja no esta abierta ");
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

						double sub = precioCompra*kgCOV;

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
							System.out.println("No Se Puede Pagar Compra");
						}

					}else{
						System.out.println("La caja no esta abierta ");
					}

				break;
				case 4: //Estadisticas

				break;
				case 5: //Ver Inventario

				break;
				case 6: //Cierre

				break;
				case 7: //Logs
					if(logs[0]!=null){
						for(int i=indiceLog-1;i>=0;i--){
							System.out.println(logs[i]);
						}
					}					
				break;
				case 8: /*Salir del sistema*/ break;
				default:
				System.out.println("Opcion no existe!\n");
			}

		}while(optMenu!=8);

	}
}
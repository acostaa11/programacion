
import java.util.Scanner;

public class PrincipalMonroy {
	private static final int OPCION_SALIR = 5;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		MonroyPet mascota1, mascota2;
		boolean terminar= false;
		int opcion;
		
		mascota1=crearPet();
		mascota2=crearPet();
		
		do{
			mostrarMenu();
			opcion=solicitarOpcion();
			terminar=tratarMenu(mascota1,mascota2, opcion);
			
		}while (terminar== false);
		
	}
	
	
	/**
	 * Muestra las opciones del menu
	 */
	private static void mostrarMenu() {
		System.out.println("1. Dar comida");
		System.out.println("2. Comprar comida");
		System.out.println("3. Jugar");
		System.out.println("4. Información de mascotas");
		System.out.println("5. Salir");
	}
	
	
	private static boolean tratarMenu(MonroyPet mascota1, MonroyPet mascota2, int opcion) {
		boolean terminar=false;
		MonroyPet mascota;
		
		try{
			switch (opcion){
			
			case 1: // Dar comida
				mascota= eligirMascota(mascota1,mascota2);
				mascota.darComida();
				System.out.println("Tu mascota ya ha comido");
				break;
				
			case 2: //Comprar comida
				mascota=eligirMascota(mascota1,mascota2);
				mascota.comprarComida(solicitarNumeroAlimentos());
				System.out.println("Compra realizada correctamente");
				break;
			case 3: // Jugar
				mascota=eligirMascota(mascota1,mascota2);
				mascota.jugar(solicitarMinutos());
				System.out.println( "Lo hemos pasado bien ;)");
				if (MonroyPet.getTotalMinutos() > MonroyPet.MAXIMO_CONTROL_PARENTAL){
					System.out.println("Las mascotas han jugado mucho, se van a dormir ZZZZ");
					terminar=true;
				}
				break;
			case 4:
				System.out.println(mascota1);
				System.out.println(mascota2);
				break;
			case 5:
				terminar=true;
				
			}		
			
		}catch (MonroyPetException e){
			System.out.println(e.getMessage());
		}
		
		return terminar;
	}
	
	/**
	 * Crea un objeto tipo MonroyPet. Si al crearlo se produce algún error
	 * vuelve a solicitar los datos
	 * @return
	 */
	private static MonroyPet crearPet() {
		String tipo, nombre;
		MonroyPet mascota=null;
		boolean error;
		System.out.println("Vamos a crear una mascota");
		
		do{
			
			try {
				
				tipo=solicitarCadena("Introduce tipo (PERRO,GATO):");
				nombre=solicitarCadena("Introduce nombre: ");
				mascota=new MonroyPet(nombre, tipo);
				error=false;
				
			} catch (MonroyPetException e) {
				System.out.println(e.getMessage());
				System.out.println("Error al crear la mascota. Volvemos a intentarlo");
				error=true;
			}
		} while (error);
		
		return mascota;
	}

	
	private static MonroyPet eligirMascota(MonroyPet mascota1, MonroyPet  mascota2) {
		int numMascota;
		MonroyPet mascota;
		
		numMascota=solicitarMascota();
		if (numMascota == 1)
			mascota=mascota1;
		else
			mascota=mascota2;
		
		return mascota;
			
	}

	/**
	 * Metodo que solicita un entero. Si el dato que se introduce no es numerioc
	 * lo vuelve a solicitar
	 * @param mensaje mensaje que se mostrará antes de solicitar el numero
	 * @return
	 */
	private static int solicitarInt(String mensaje) {

		String cadena;
		int numero = 0;
		boolean error;

		do {
			try {
				error = false;
				System.out.println(mensaje);
				cadena = teclado.nextLine();
				numero = Integer.parseInt(cadena);
			} catch (NumberFormatException ex) {
				error = true;
				System.out.println("No es un numero");
			}
		} while (error);

		return numero;
	}
	
	/**
	 * Solicita una opcion validando que este entre 1 y 
	 * @return opcion elegida
	 */
	private static int solicitarOpcion() {
		int opc;

		do {
			opc = solicitarInt("Introduce opc (1- " + OPCION_SALIR + "):");
		} while (opc < 0 || opc > OPCION_SALIR);
		return opc;
	}
	
	/**
	 * Solicita el numero de mascota, validando que sea 1 o 2
	 * @return mascota elegida (1 o 2)
	 */
	private static int solicitarMascota(){
		int mascota;

		do {
			mascota = solicitarInt("Introduce la mascota(1-2): ");
		} while (!(mascota==1 || mascota==2));
		return mascota;
	}
	
	/**
	 * Solicita un dato de tipo cadena por teclado, mostrando un mensaje previamente
	 * @param mensaje
	 * @return
	 */
	private static String solicitarCadena( String mensaje) {
		String cadena;
		
		System.out.println(mensaje);
		cadena=teclado.nextLine();
		cadena=cadena.toUpperCase();
		return cadena;
	}
	

	/**
	 * Solicita el numero de alimentos 
	 * @return numero de alimentos
	 */
	private static int solicitarNumeroAlimentos(){
		int alimentos;

		do {
			alimentos = solicitarInt("Introduce numero de alimentos: ");
		} while (alimentos<0);
		return alimentos;
	}
	
	/**
	 * Solicita el numero de minutos jugados
	 * @return minutos jugados
	 */
	private static int solicitarMinutos(){
		int minutos;

		do {
			minutos = solicitarInt("Introduce numero de minutos: ");
		} while (minutos<0);
		return minutos;
	}
	
	

}
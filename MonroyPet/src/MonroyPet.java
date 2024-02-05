
public class MonroyPet {
	private static final int PUNTOS_INICIAL = 0;
	public static final int ALIMENTOS_INICIAL = 2;
	public static final int PUNTOS_POR_ALIMENTO=30;
	public static final int PUNTOS_POR_MINUTO=3;
	public static final int MAXIMO_MINUTOS_JUGADO=20;
	public static final int MAXIMO_CONTROL_PARENTAL = 100;
	
	// Atributo estático para almacenar el total de minutos jugados entre
	// todas las mascotas
	private static int totalMinutos=0;
	
	// Atributos 
	private String nombre;
	private String tipo; // PERRO O GATO
	private int puntos;
	private int alimentos;

	
	
	public MonroyPet ( String nombre, String tipo) throws MonroyPetException{
		setNombre(nombre);
		setTipo(tipo);
		this.alimentos=ALIMENTOS_INICIAL;
		this.puntos=PUNTOS_INICIAL;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public static int getTotalMinutos() {
		return totalMinutos;
		
	}

	public String getTipo() {
		return tipo;
	}

	// Privada, no se admite cambios de tipo de mascota
	private void setTipo(String tipo) throws MonroyPetException {
		tipo= tipo.toUpperCase();
		if ( !( tipo.equals("PERRO") || tipo.equals("GATO")))
			throw new MonroyPetException("El tipo de la mascota no es correcto");
		this.tipo = tipo;
	}

	public int getAlimentos() {
		return alimentos;
	}


	@Override
	public String toString() {
		String infor;
		if ( tipo.equals("PERRO"))
			infor="Pet nombre= " + nombre + ", tipo=" + tipo + ", huesos!!= " + alimentos
					+ "  puntos " + puntos;
		else
			infor="Pet nombre=" + nombre + ", tipo=" + tipo + ", pescados= " + alimentos
					+ "  puntos " + puntos;
		return infor;
	}

	public void darComida() throws MonroyPetException{
		if (alimentos ==0)
			throw new MonroyPetException("No tienes comida, comprala");
		alimentos--;
	}
	
	public void comprarComida(int alimentosAComprar) throws MonroyPetException{
		int puntosGastar= alimentosAComprar*PUNTOS_POR_ALIMENTO;
		if (alimentosAComprar <= 0)
			throw new MonroyPetException("Numero de alimentos incorrecto");
		
		if ( puntosGastar > puntos)
			throw new MonroyPetException("No puedes comprar porque no tienes puntos");
			
		puntos= puntos- puntosGastar;
		alimentos= alimentos + alimentosAComprar;
	}
	
	public void jugar (int minutos) throws MonroyPetException{
		if (minutos <= 0 || minutos > MAXIMO_MINUTOS_JUGADO)
			throw new MonroyPetException("Minutos incorrectos, no puede superar los " + MAXIMO_MINUTOS_JUGADO);
		
		puntos = puntos + minutos*PUNTOS_POR_MINUTO;
		
		// Control parental, acumulo el numero de minutos jugados entre todas 
		// las mascotas
		totalMinutos= totalMinutos + minutos;
	}
	
	
	public boolean equals (MonroyPet otro){
		boolean iguales=false;
		
		if ( this.nombre.equals(otro.nombre ) && this.tipo.equals(otro.tipo))
			iguales=true;
		
		return iguales;
	}
	

	
	
	
	
}

import java.util.Scanner;

class Intervalo {

    // Atributos
    private double extremoInferior;
    private double extremoSuperior;

    public static final double ORIGEN = 0.00;

    // Constructores

    public Intervalo() {
        setExtremoSuperior(ORIGEN);
        setExtremoInferior(ORIGEN);
    }

    public Intervalo(double extremoInferior, double extremoSuperior) {
		/*
		double aux = extremoSuperior;
		if(extremoSuperior < extremoInferior) {
			extremoSuperior = extremoInferior;
			extremoInferior = aux;
		}
//		this.extremoInferior = extremoInferior;
		setExtremoInferior(extremoInferior);
		this.extremoSuperior = extremoSuperior;
		*/
        setExtremoInferior(Math.min(extremoInferior, extremoSuperior));
        setExtremoSuperior(Math.max(extremoInferior, extremoSuperior));
    }

    public Intervalo(Intervalo intervalo) {
        /* A DESARROLLAR
         Constructor que recibe un único extremo
         this();
        if(intervalo == null) {
            this.extremoInferior = intervalo.extremoInferior;
            this.extremoSuperior = intervalo.extremoSuperior;
        }*/

        if(intervalo == null) {
            setExtremoSuperior(ORIGEN);
            setExtremoInferior(ORIGEN);
        }else{
            setExtremoSuperior(intervalo.getExtremoSuperior());
            setExtremoInferior(intervalo.getExtremoInferior());
        }
    }

    public Intervalo(double extremo) {
        this();
        if(extremo > extremoSuperior){
            extremoSuperior = extremo;
        }
        if(extremo < extremoInferior){
            extremoInferior = extremo;
        }
    }

    // A DESARROLLAR
    // Se pide crear todos los métodos necesarios para que
    // los extremos de los intervalos tengan como máximo
    // 2 decimales resultado de redondear el valor proporcionado
    // y que cualquier valor devuelto también cumpla la restricción

    // Métodos públicos

    public void recoger() {
        // recuperar por teclado los extremos de un intervalo
        double extremoInferior, extremoSuperior;

        // NOTA: la "," como separador decimal
        Scanner scanner = new Scanner(System.in);
        System.out.print("Extremo inferior: ");
        extremoInferior = scanner.nextDouble();
        System.out.print("Extremo superior: ");
        extremoSuperior = scanner.nextDouble();
        scanner.close();
		/*
		double aux = extremoSuperior;
		if(extremoSuperior < extremoInferior) {
			extremoSuperior = extremoInferior;
			extremoInferior = aux;
		}
		this.extremoInferior = extremoInferior;
		this.extremoSuperior = extremoSuperior;
		*/
        setExtremoInferior(Math.min(extremoInferior, extremoSuperior));
        setExtremoSuperior(Math.max(extremoInferior, extremoSuperior));
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [" + getExtremoInferior() + ", " + getExtremoSuperior() + "]";
    }

    public void mostrar() {
        // muestra por pantalla el intervalo
        System.out.println(this.toString());
    }

    public double longitud() {
        // devuelve la longitud del intervalo
        return redondea(getExtremoSuperior() - getExtremoInferior());
    }

    public double puntoMedio() {
        // devuelve el punto medio del intervalo
        return redondea(getExtremoInferior() + redondea(longitud() / 2));
    }

    @Override
    public boolean equals(Object obj) {
        // A DESARROLLAR
        // comprobar que <obj> es de clase <Intervalo>
        boolean iguales = false;
        if (obj instanceof Intervalo) {
            iguales = this.igual((Intervalo) obj);
        }
        return iguales;
    }

    public boolean igual(Intervalo intervalo) {
        boolean iguales = false;
        if(intervalo != null) {
            iguales = getExtremoInferior() == intervalo.getExtremoInferior()
                    && getExtremoSuperior() == intervalo.getExtremoSuperior();
        }
        return iguales;
    }

    public boolean distinto(Intervalo intervalo) {
        return !this.igual(intervalo);
    }

    public boolean anterior(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        return (intervalo != null && getExtremoSuperior() < intervalo.getExtremoInferior());
    }

    public boolean posterior(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        return (intervalo != null && getExtremoInferior() > intervalo.getExtremoSuperior());
    }


    public boolean incluye(double punto) {
        return getExtremoInferior() <= punto && punto <= getExtremoSuperior();
    }

    public boolean incluye(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        return intervalo!= null &&
                incluye(intervalo.getExtremoInferior()) &&
                incluye(intervalo.getExtremoSuperior());
    }

    public boolean intersecta(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        return intervalo != null && (incluye(intervalo.getExtremoInferior())
                    || incluye(intervalo.getExtremoSuperior())
                    || intervalo.incluye(getExtremoInferior())
                    || intervalo.incluye(getExtremoSuperior()));
    }

    public Intervalo interseccion(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        Intervalo resultado = null;
		/*
		if(this.intersecta(intervalo)) {
			if (this.incluye(intervalo)) {
				resultado = intervalo.copia();
			} else if (intervalo.incluye(this)) {
				resultado = this.copia();
			} else if (this.incluye(intervalo.extremoInferior)) {
				resultado = new Intervalo(intervalo.extremoInferior, this.extremoSuperior);
			} else if (this.incluye(intervalo.extremoSuperior)) {
				resultado = new Intervalo(this.extremoInferior, intervalo.extremoSuperior);
			}
		}
		 */
        double extremoInferior, extremoSuperior;
        if (intervalo != null) {
            if (this.intersecta(intervalo)) {
                extremoInferior = Math.max(getExtremoInferior(), intervalo.getExtremoInferior());
                extremoSuperior = Math.min(getExtremoSuperior(), intervalo.getExtremoSuperior());
                resultado = new Intervalo(extremoInferior, extremoSuperior);
            }
        }
        return resultado;
    }

    public Intervalo diferencia(Intervalo intervalo) {
        // A DESARROLLAR
        // Intervalo que hay entre dos intervalos que NO intersectan
        // La diferencia entre [0.99, 5.99] y [8.0, 10.0] es [5.99, 8.0]
        // La diferencia entre [0.99, 5.99] y [3.0, 17.0] es null
        // comprobar que el parámetro <intervalo> no es nulo
        Intervalo resultado = null;
        if(!intersecta(intervalo)){
            resultado = new Intervalo(redondea(Math.max(intervalo.getExtremoInferior(),getExtremoInferior())),
                    redondea(Math.min(intervalo.getExtremoSuperior(),getExtremoSuperior())));
        }
        return resultado;
    }

    public double distancia (Intervalo intervalo) {
        // A DESARROLLAR
        // Distancia que hay entre dos intervalos que NO intersectan
        // La distancia entre [0.99, 5.99] y [8.0, 10.0] es 2.01
        // La distancia entre [0.99, 5.99] y [3.0, 17.0] es 0.0
        // comprobar que el parámetro <intervalo> no es nulo
        Double resultado = 0.0;
        if(!intersecta(intervalo)){
            resultado = redondea(Math.min(intervalo.getExtremoSuperior(),getExtremoSuperior()) -
                            Math.max(intervalo.getExtremoInferior(),getExtremoInferior()));
        }
        return resultado;
    }


    @Override
    protected Object clone() {
        return this.copia();
    }

    public Intervalo copia() {
        return new Intervalo(this);
    }

    public Intervalo suma(Intervalo intervalo) {
        // A DESARROLLAR
        // comprobar que el parámetro <intervalo> no es nulo
        Intervalo resultado = null;
		/*
		if(this.intersecta(intervalo)) {
			if(this.incluye(intervalo)){
				resultado = this.copia();
			} else if (intervalo.incluye(this)) {
				resultado = intervalo.copia();
			} else if (this.incluye(intervalo.extremoInferior)) {
				resultado = new Intervalo(this.extremoInferior, intervalo.extremoSuperior);
			} else if (this.incluye(intervalo.extremoSuperior)) {
				resultado = new Intervalo(intervalo.extremoInferior, this.extremoSuperior);
			}
		}
		*/
        double extremoInferior, extremoSuperior;
        if (this.intersecta(intervalo)) {
            extremoInferior = Math.min(getExtremoInferior(), intervalo.getExtremoInferior());
            extremoSuperior = Math.max(getExtremoSuperior(), intervalo.getExtremoSuperior());
            resultado = new Intervalo(extremoInferior, extremoSuperior);
        }
        return resultado;
    }

    public Intervalo desplazar(double valor) {
        return new Intervalo(getExtremoInferior() + valor, getExtremoSuperior() + valor);
    }

    public void autoDesplazar(double valor) {
        setExtremoInferior(getExtremoInferior() + valor);
        setExtremoSuperior(getExtremoSuperior() + valor);
    }

    /*public void (double extremoInferior) {
        this.extremoInferior = Math.round(extremoInferior * 100d) / 100d);
    }*/

    public double redondea(double valor) {
        return Math.round((valor * 100d)) / 100d;
    }

    public void setExtremoInferior(double extremoInferior) {
        this.extremoInferior = redondea(extremoInferior);
    }

    public void setExtremoSuperior(double extremoSuperior) {
        this.extremoSuperior = redondea(extremoSuperior);
    }

    public double getExtremoInferior() {
        return redondea(extremoInferior);
    }

    public double getExtremoSuperior() {
        return redondea(extremoSuperior);
    }

    public static void main(String[] args) {
        Intervalo i1 = new Intervalo();
        Intervalo i2 = new Intervalo(2.15, 7.15);
        Intervalo i3 = new Intervalo(i2);
        Intervalo i4 = new Intervalo(8.0, 10.0);
        Intervalo i5 = new Intervalo(3.0, 17.0);
        Intervalo i6 = new Intervalo(3.0);
        i1.mostrar();
        i2.mostrar();
        i3.mostrar();
        i4.mostrar();
        i5.mostrar();
        i6.mostrar();
        System.out.println("longitud "+i1+": "+i1.longitud());
        System.out.println("longitud "+i2+": "+i2.longitud());
        System.out.println("longitud "+i4+": "+i4.longitud());
        System.out.println("punto medio "+i1+": "+i1.puntoMedio());
        System.out.println("punto medio "+i2+": "+i2.puntoMedio());
        System.out.println("punto medio "+i4+": "+i4.puntoMedio());
        System.out.println("¿iguales "+i2+" e "+i3+"?: "+i2.igual(i3));
        System.out.println("¿iguales "+i2+" e "+i1+"?: "+i2.igual(i1));
        System.out.println("¿distintos "+i2+" e "+i3+"?: "+i2.distinto(i3));
        System.out.println("¿distintos "+i2+" e "+i1+"?: "+i2.distinto(i1));
        System.out.println("¿anterior "+i2+" a "+i4+"?: "+i2.anterior(i4));
        System.out.println("¿posterior "+i2+" a "+i4+"?: "+i2.posterior(i4));
        System.out.println("¿incluye "+i2+" al punto 1.5?: "+i2.incluye(1.5));
        System.out.println("¿incluye "+i2+" al punto 3.5?: "+i2.incluye(3.5));
        System.out.println("¿incluye "+i2+" al "+i4+"?: "+i2.incluye(i4));
        System.out.println("¿incluye "+i5+" al "+i4+"?: "+i5.incluye(i4));
        System.out.println("¿intersecta "+i2+" al "+i5+"?: "+i2.intersecta(i5));
        System.out.println("¿intersecta "+i4+" al "+i5+"?: "+i4.intersecta(i5));
        System.out.println("intersección "+i2+" e "+i5+"?: "+i2.interseccion(i5));
        System.out.println("intersección "+i4+" e "+i5+"?: "+i4.interseccion(i5));
        System.out.println("¿Suma "+i2+" e "+i4+"?: "+i2.suma(i4));
        System.out.println("¿Suma "+i2+" e "+i5+"?: "+i2.suma(i5));
        System.out.println("Desplazar "+ i2 +" con 3.7: "+i2.desplazar(3.7));
        System.out.print("Autodesplazar "+ i2 +" con -1.16: ");
        i2.autoDesplazar(-1.16);
        i2.mostrar();

        System.out.println("¿Diferencia "+i2+" e "+i4+"?: "+i2.diferencia(i4));
        System.out.println("¿Diferencia "+i2+" e "+i5+"?: "+i2.diferencia(i5));
        System.out.println("¿Distancia "+i2+" e "+i4+"?: "+i2.distancia(i4));
        System.out.println("¿Distancia "+i2+" e "+i5+"?: "+i2.distancia(i5));

        System.out.println(i1 +" antes de recoger los valores...");
        i1.recoger();
        System.out.println(i1 +" despúes de recoger los valores...");
    }
}


public class Intervalo3P extends Intervalo {
    private double puntoIntermedio;


    public Intervalo3P(){
        // llamada implícita al constructor vacío de la clase padre
        super();
        this.puntoIntermedio = ORIGEN;
    }

    public Intervalo3P(double extremoInferior, double extremoSuperior) {
        super(extremoInferior, extremoSuperior);
        // this.puntoIntermedio = super.puntoMedio();
        this.puntoIntermedio = this.puntoMedio();
    }

    public Intervalo3P(double extremoInferior, double extremoSuperior, double puntoIntermedio) {
        super(extremoInferior, extremoSuperior);
        this.puntoIntermedio = puntoIntermedio;
    }

    public Intervalo3P(Intervalo intervalo) {
        super(intervalo);
        //this.puntoIntermedio = super.puntoMedio();
        //this.puntoIntermedio = this.puntoMedio();
        this.puntoIntermedio = intervalo.puntoMedio();
    }

    @Override
    protected Object clone() {
        return new Intervalo3P(this);
    }

    public Intervalo3P copia() {
        return (Intervalo3P) this.clone();
    }

    public Intervalo3P(Intervalo3P intervalo) {
        super(intervalo);
        this.puntoIntermedio = intervalo.puntoIntermedio;
    }

    public Intervalo3P(Intervalo intervalo, double puntoIntermedio) {
        super(intervalo);
        this.puntoIntermedio = puntoIntermedio;
    }

    public Intervalo3P(double extremo) {
        super(extremo);
        //this.puntoIntermedio = super.puntoMedio();
        this.puntoIntermedio = this.puntoMedio();
    }

    @Override
    public String toString() {
        /*
            return this.getClass().getName() +
                    " [" + this.getExtremoInferior() +
                    ", " + this.puntoIntermedio +
                    ", " + this.getExtremoSuperior() + "]";
        */
        return super.toString().replaceFirst(",", ", "+this.puntoIntermedio +",");
    }

    public boolean igual(Intervalo3P intervalo){
        return super.igual(intervalo) && this.puntoIntermedio == intervalo.puntoIntermedio;
    }

    public static void main(String[] args) {
        Intervalo i0 = new Intervalo();
        Intervalo i1 = new Intervalo(5.0);
        Intervalo i2 = new Intervalo(4.0, 8.0);
        Intervalo3P i3 = new Intervalo3P();
        Intervalo3P i4 = new Intervalo3P(5.0);
        Intervalo3P i5 = new Intervalo3P(4.0, 8.0);
        Intervalo3P i6 = new Intervalo3P(4.0, 8.0, 7.0);

        Intervalo lista[] = new Intervalo[]{i0, i1, i2, i3, i4, i5, i6};

        for(Intervalo i: lista){
            i.mostrar();
        }
        System.out.println("-------------");
        System.out.println("¿iguales "+lista[1]+" al "+lista[4]+"?: "+lista[1].igual(lista[4]));
        System.out.println("¿iguales "+i1+" al "+i4+"?: "+i1.igual(i4));
        System.out.println("¿iguales "+lista[5]+" al "+lista[2]+"?: "+lista[5].igual(lista[2]));
        System.out.println("¿iguales "+i5+" al "+i2+"?: "+i5.igual(i2));
        System.out.println("-------------");
        System.out.println("¿iguales "+lista[5]+" al "+lista[6]+"?: "+lista[5].igual(lista[6]));
        System.out.println("¿iguales "+i5+" al "+i6+"?: "+i5.igual(i6));
        System.out.println("¿iguales "+i5+" al "+i6+"?: "+((Intervalo)i5).igual(i6));
        System.out.println("¿iguales "+i5+" al "+i6+"?: "+i5.igual((Intervalo)i6));
    }
}

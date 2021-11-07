package calculator;

public class Complex {
    private static double EPS = 1e-12;
    private double Re, Im;

    Complex(double Re, double Im){
        this.Re = Re;
        this.Im =Im;
    }
    Complex(){
        this.Re = 0.0;
        this.Im = 0.0;
    }
    Complex(double Re){
        this.Re = Re;
        this.Im = 0.0;
    }
    Complex(Complex Z){
        this.Re = Z.getRe();
        this.Im = Z.getIm();
    }

    public double getIm(){
        return Im;
    }
    public double getRe(){
        return Re;
    }
    public Complex getComplex(){
        return new Complex(Re, Im);
    }
    public void setRe(double Re){
        this.Re = Re;
    }
    public void setIm(double Im){
        this.Im = Im;
    }
    public void setComplex(Complex c){
        this.Re = c.getRe();
        this.Im = c.getIm();
    }

    public double getLen(){
        return Math.sqrt(Im * Im + Re * Re);
    }
    public double getDeg(){
        return Math.atan2(Im, Re);
    }

    public void add(Complex c){
        Re += c.getRe();
        Im += c.getIm();
    }
    public void sub(Complex c){
        Re -= c.getRe();
        Im -= c.getIm();
    }
    public void divide(Complex c){
        double len = c.getLen();
        len *= len;

        Complex conjugate = c.getConjugate();
        Complex current = this.getComplex();
        current.multiply(conjugate);

        current.Re /= len;
        current.Im /= len;
        Re = current.getRe();
        Im = current.Im;
    }
    public void multiply(Complex c){
        double help = Re * c.getRe() - Im * c.getIm();
        Im = Re * c.getIm() + Im * c.getRe();
        Re = help;
    }
    public Complex getConjugate(){
        return new Complex(Re, -Im);
    }
    public boolean isReal(){
        return Math.abs(Im) < EPS;
    }
    public boolean equals(Complex c){
        return ((Math.abs(Im - c.getIm()) < EPS) && (Math.abs(Re - c.getRe()) < EPS));
    }
    public void printComplex(){
        if (this.isReal())
            System.out.println(Re);
        else
            System.out.println(Re + (Im > 0.0 ? "+" : "") + Im + "i");
    }

    public String makeString() {
        if (this.isReal())
            return Re + "";
        else
            return  Re + (Im > 0.0 ? "+" : "") + Im + "i";
    }
}

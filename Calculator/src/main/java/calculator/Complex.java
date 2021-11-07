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
        this.multiply(c);
        double len = c.getLen();
        this.Im /= len;
        this.Re /= len;
    }
    public void multiply(Complex c){
        double help = Re * c.getRe() - Im * c.getIm();
        Im = Re * c.getIm() + Im * c.getRe();
        Re = help;
    }
    public Complex getConjugate(){
        return new Complex(Re, (-1)*Im);
    }
    public boolean isReal(){
        return Math.abs(Im) < EPS;
    }
    public boolean equals(Complex c){
        return ((Math.abs(Im - c.getIm()) < EPS) && (Math.abs(Re - c.getRe()) < EPS));
    }
    public void printComplex(){
        System.out.println(Re + (Im > 0.0 ? "+" : "") + Im);
    }

    public String makeString() {
        return  Re + (Im > 0.0 ? "+" : "") + Im;
    }
}

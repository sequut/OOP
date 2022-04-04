public class KindPizza {
    public enum Size {SMALL, MEDIUM, BIG}
    public enum Flavour {BBQ, FOURCHEESES, MARGARITA, VEGAN, SECRET}

    private final Size size;
    private final Flavour flavour;
    private final int requiredTime;

    public KindPizza(Flavour flavour, Size size){
        this.size = size;
        this.flavour = flavour;
        this.requiredTime = calculateTime(size, flavour);
    }

    private int calculateTime(Size size, Flavour flavour){
        int answer;
        switch (flavour){
            case BBQ, MARGARITA -> answer = 2;
            case FOURCHEESES -> answer = 3;
            case SECRET -> answer = 4;
            default -> answer = 1;
        }

        if (size == Size.BIG) {
            answer *= 2;
        }
        return answer;
    }

    public int getTime(){
        return requiredTime;
    }

    public int getSize(){
        return size.ordinal();
    }

    public String getInfo(){
        return (size + " " + flavour + " pizza");
    }
}

public class runPizzeria {
    private final PizzeriaData pizzeriaData;

    runPizzeria(PizzeriaData pizzeriaData){
        this.pizzeriaData = pizzeriaData;

        pizzeriaData.writeAllData();
    }
}

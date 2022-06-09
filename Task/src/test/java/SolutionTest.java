import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test
    public void TestExample(){
        Solution solve = new Solution("STWSWTPPTPTTPWPP", "Human");
        Assertions.assertEquals(10, Solution.getResult());
    }

    @Test
    public void TestHuman(){
        Solution solve = new Solution("SSSSWTWTPTTPPWSW", "Human");
        Assertions.assertEquals(12, Solution.getResult());
    }

    @Test
    public void TestSwamper(){
        Solution solve = new Solution("TTTTPSPSTPPTSWWS", "Swamper");
        Assertions.assertEquals(12, Solution.getResult());
    }

    @Test
    public void TestWoodman(){
        Solution solve = new Solution("SSSSWTWTPTTPPWSW", "Woodman");
        Assertions.assertEquals(14, Solution.getResult());
    }
}
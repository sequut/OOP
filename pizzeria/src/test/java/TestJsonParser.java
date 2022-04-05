import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJsonParser {
    @Test
    public void TestParser() throws Exception {
        jsonParser parser = new jsonParser("test.json");

        PizzeriaData pizzeriaData = new PizzeriaData(parser.GetData().getStorage(), parser.GetData().getDelivery(), parser.GetData().getBackersExp());

        Assertions.assertEquals(10, pizzeriaData.getStorage());

        int[] exp = {12, 5, 10, 3};
        Assertions.assertArrayEquals(exp, pizzeriaData.getBackersExp());

        int[] deli = {5, 7, 6};
        Assertions.assertArrayEquals(deli, pizzeriaData.getDelivery());
    }
}

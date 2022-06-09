public class Solution {
    static String grid;
    static String race;

    public Solution(String grid, String race){
        Solution.grid = grid;
        Solution.race = race;
    }

    private static void ConvertGrid(int[][] grid, String race, String startGrid){
        for (int i = 0; i < 16; i++){
            switch (race) {
                case "Woodman" -> {
                    switch (startGrid.charAt(i)) {
                        case 'S', 'W' -> grid[i / 4][i % 4] = 3;
                        default -> grid[i / 4][i % 4] = 2;
                    }
                }
                case "Swamper" -> {
                    switch (startGrid.charAt(i)) {
                        case 'S', 'W', 'P' -> grid[i / 4][i % 4] = 2;
                        default -> grid[i / 4][i % 4] = 5;
                    }
                }
                case "Human" -> {
                    switch (startGrid.charAt(i)) {
                        case 'S' -> grid[i / 4][i % 4] = 5;
                        case 'W' -> grid[i / 4][i % 4] = 2;
                        case 'P' -> grid[i / 4][i % 4] = 1;
                        default -> grid[i / 4][i % 4] = 3;
                    }
                }
                default -> {
                }
            }
        }
    }

    public static int getResult(){
        int[][] gridInt = new int[4][4];
        ConvertGrid(gridInt, race, grid);

        int [][] answer = new int[4][4];
        for (int i = 1; i < 4; i++){
            answer[0][i] = answer[0][i - 1] + gridInt[0][i];
        }

        for (int i = 1; i < 4; i++){
            answer[i][0] = answer[i - 1][0] + gridInt[i][0];

            for (int j = 1; j < 4; j++){
                if (answer[i - 1][j] > answer[i][j - 1]){
                    answer[i][j] = answer[i][j - 1] + gridInt[i][j];
                }
                else {
                    answer[i][j] = answer[i - 1][j] + gridInt[i][j];
                }
            }
        }
        return answer[3][3];
    }

    public static void main(String[] args) {
        new Solution("STWSWTPPTPTTPWPP", "Human");
        System.out.println(getResult());
    }
}

package cadastro;

public class Test {
    public static void main(String[] args) {
        String eu1 = "Guilherme";
        try {
            int eu = Integer.parseInt(eu1);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}

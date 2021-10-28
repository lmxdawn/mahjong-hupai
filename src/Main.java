import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // int[] hai = {1,1,1,10,11,12,33,34,35,27,5,6,7};
        // int[] hai = {1,1,1,10,11,12,33,34,35,27,27,28,29};
        // int[] hai = {1,2,3,11,11,33,33};
        // int[] hai = {1,2,3,11,12,13,14,15,16,17};
        // int[] hai = {1,3,3,11,12,13,14,15,16,17,18,19,1};
        // int[] hai = {1,1,1,2,10,11,12,27,27,27,27,28,28,28,28};
        int[] hai = {1,1,9,9,10,10,10,12,12,12,27,27,27};

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < hai.length; i++) {
            arr.add(hai[i]);
        }

        HuPai huPai = new HuPai();

        List<HuPai.Fan> fan = huPai.getFan(arr);

        fan.forEach(v -> {
            System.out.printf("可以胡牌的牌：%s\n", v);
        });
    }

}

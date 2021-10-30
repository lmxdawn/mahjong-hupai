import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        // int[] hai = {1,1,1,10,11,12,33,34,35,27,5,6,7};
        // int[] hai = {1,1,1,10,11,12,33,34,35,27,27,28,29};
        // int[] hai = {1,2,3,11,11,33,33};
        // int[] hai = {1,2,3,11,12,13,14,15,16,17};
        // int[] hai = {1,3,3,11,12,13,14,15,16,17,18,19,1};
        // int[] hai = {1,1,1,2,10,11,12,27,27,27,27,28,28,28,28};
        int[] hai = {1,1,1,1,27,28,28,28,28,29,29,29,29};

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < hai.length; i++) {
            arr.add(hai[i]);
        }

        Algorithm algorithm = new Algorithm();

        Set<Integer> huPai = algorithm.getHuPai(arr);
        huPai.forEach(v -> {
            System.out.printf("可以胡牌的牌：%s\n", v);
        });


        // 生成所有的组合
        List<Integer> all = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            all.add(i);
            all.add(i);
            all.add(i);
            all.add(i);
            all.add(Integer.valueOf(String.format("1%s", i)));
            all.add(Integer.valueOf(String.format("1%s", i)));
            all.add(Integer.valueOf(String.format("1%s", i)));
            all.add(Integer.valueOf(String.format("1%s", i)));
            all.add(Integer.valueOf(String.format("2%s", i)));
            all.add(Integer.valueOf(String.format("2%s", i)));
            all.add(Integer.valueOf(String.format("2%s", i)));
            all.add(Integer.valueOf(String.format("2%s", i)));
        }

        Collections.sort(all);

        int len = all.size();

        if (len > 0) {
            // return;
        }

        List<List<Integer>> ll = new ArrayList<>();
        Map<Integer, Integer> hvMap = new HashMap<>();
        List<Integer> llv = new ArrayList<>();
        int i = 0;
        int iu = 0;
        for (int i1 = 0; i1 < len; i1++) {
            int iv1 = all.get(i1);
            // 先清空
            llv.clear();
            llv.add(iv1);
            // 先清空
            hvMap.clear();
            // 判断是否有效
            for (int hi = 0; hi < llv.size(); hi++) {
                if (hvMap.containsKey(llv.get(hi))) {
                    hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                } else {
                    hvMap.put(llv.get(hi), + 1);
                }
            }
            if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                // 牌型不符合规则
                continue;
            }
            for (int i2 = i1; i2 < len; i2++) {
                int iv2 = all.get(i2);
                // 先清空
                llv.clear();
                llv.add(iv1);
                llv.add(iv2);
                // 先清空
                hvMap.clear();
                // 判断是否有效
                for (int hi = 0; hi < llv.size(); hi++) {
                    if (hvMap.containsKey(llv.get(hi))) {
                        hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                    } else {
                        hvMap.put(llv.get(hi), + 1);
                    }
                }
                if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                    // 牌型不符合规则
                    continue;
                }
                for (int i3 = i2; i3 < len; i3++) {
                    int iv3 = all.get(i3);
                    // 先清空
                    llv.clear();
                    llv.add(iv1);
                    llv.add(iv2);
                    llv.add(iv3);
                    // 先清空
                    hvMap.clear();
                    // 判断是否有效
                    for (int hi = 0; hi < llv.size(); hi++) {
                        if (hvMap.containsKey(llv.get(hi))) {
                            hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                        } else {
                            hvMap.put(llv.get(hi), + 1);
                        }
                    }
                    if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                        // 牌型不符合规则
                        continue;
                    }
                    for (int i4 = i3; i4 < len; i4++) {
                        int iv4 = all.get(i4);
                        // 先清空
                        llv.clear();
                        llv.add(iv1);
                        llv.add(iv2);
                        llv.add(iv3);
                        llv.add(iv4);
                        // 先清空
                        hvMap.clear();
                        // 判断是否有效
                        for (int hi = 0; hi < llv.size(); hi++) {
                            if (hvMap.containsKey(llv.get(hi))) {
                                hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                            } else {
                                hvMap.put(llv.get(hi), + 1);
                            }
                        }
                        if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                            // 牌型不符合规则
                            continue;
                        }
                        for (int i5 = i4; i5 < len; i5++) {
                            int iv5 = all.get(i5);
                            // 先清空
                            llv.clear();
                            llv.add(iv1);
                            llv.add(iv2);
                            llv.add(iv3);
                            llv.add(iv4);
                            llv.add(iv5);
                            // 先清空
                            hvMap.clear();
                            // 判断是否有效
                            for (int hi = 0; hi < llv.size(); hi++) {
                                if (hvMap.containsKey(llv.get(hi))) {
                                    hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                } else {
                                    hvMap.put(llv.get(hi), + 1);
                                }
                            }
                            if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                // 牌型不符合规则
                                continue;
                            }
                            for (int i6 = i5; i6 < len; i6++) {
                                int iv6 = all.get(i6);
                                // 先清空
                                llv.clear();
                                llv.add(iv1);
                                llv.add(iv2);
                                llv.add(iv3);
                                llv.add(iv4);
                                llv.add(iv5);
                                llv.add(iv6);
                                // 先清空
                                hvMap.clear();
                                // 判断是否有效
                                for (int hi = 0; hi < llv.size(); hi++) {
                                    if (hvMap.containsKey(llv.get(hi))) {
                                        hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                    } else {
                                        hvMap.put(llv.get(hi), + 1);
                                    }
                                }
                                if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                    // 牌型不符合规则
                                    continue;
                                }
                                for (int i7 = i6; i7 < len; i7++) {
                                    int iv7 = all.get(i7);
                                    // 先清空
                                    llv.clear();
                                    llv.add(iv1);
                                    llv.add(iv2);
                                    llv.add(iv3);
                                    llv.add(iv4);
                                    llv.add(iv5);
                                    llv.add(iv6);
                                    llv.add(iv7);
                                    // 先清空
                                    hvMap.clear();
                                    // 判断是否有效
                                    for (int hi = 0; hi < llv.size(); hi++) {
                                        if (hvMap.containsKey(llv.get(hi))) {
                                            hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                        } else {
                                            hvMap.put(llv.get(hi), + 1);
                                        }
                                    }
                                    if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                        // 牌型不符合规则
                                        continue;
                                    }
                                    for (int i8 = i7; i8 < len; i8++) {
                                        int iv8 = all.get(i8);
                                        // 先清空
                                        llv.clear();
                                        llv.add(iv1);
                                        llv.add(iv2);
                                        llv.add(iv3);
                                        llv.add(iv4);
                                        llv.add(iv5);
                                        llv.add(iv6);
                                        llv.add(iv7);
                                        llv.add(iv8);
                                        // 先清空
                                        hvMap.clear();
                                        // 判断是否有效
                                        for (int hi = 0; hi < llv.size(); hi++) {
                                            if (hvMap.containsKey(llv.get(hi))) {
                                                hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                            } else {
                                                hvMap.put(llv.get(hi), + 1);
                                            }
                                        }
                                        if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                            // 牌型不符合规则
                                            continue;
                                        }
                                        for (int i9 = i8; i9 < len; i9++) {
                                            int iv9 = all.get(i9);
                                            for (int i10 = i9; i10 < len; i10++) {
                                                int iv10 = all.get(i10);
                                                // 先清空
                                                llv.clear();
                                                llv.add(iv1);
                                                llv.add(iv2);
                                                llv.add(iv3);
                                                llv.add(iv4);
                                                llv.add(iv5);
                                                llv.add(iv6);
                                                llv.add(iv7);
                                                llv.add(iv8);
                                                llv.add(iv9);
                                                llv.add(iv10);
                                                // 先清空
                                                hvMap.clear();
                                                // 判断是否有效
                                                for (int hi = 0; hi < llv.size(); hi++) {
                                                    if (hvMap.containsKey(llv.get(hi))) {
                                                        hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                                    } else {
                                                        hvMap.put(llv.get(hi), + 1);
                                                    }
                                                }
                                                if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                                    // 牌型不符合规则
                                                    continue;
                                                }
                                                for (int i11 = i10; i11 < len; i11++) {
                                                    int iv11 = all.get(i11);
                                                    // 先清空
                                                    llv.clear();
                                                    llv.add(iv1);
                                                    llv.add(iv2);
                                                    llv.add(iv3);
                                                    llv.add(iv4);
                                                    llv.add(iv5);
                                                    llv.add(iv6);
                                                    llv.add(iv7);
                                                    llv.add(iv8);
                                                    llv.add(iv9);
                                                    llv.add(iv10);
                                                    llv.add(iv11);
                                                    // 先清空
                                                    hvMap.clear();
                                                    // 判断是否有效
                                                    for (int hi = 0; hi < llv.size(); hi++) {
                                                        if (hvMap.containsKey(llv.get(hi))) {
                                                            hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                                        } else {
                                                            hvMap.put(llv.get(hi), + 1);
                                                        }
                                                    }
                                                    if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                                        // 牌型不符合规则
                                                        continue;
                                                    }
                                                    for (int i12 = i11; i12 < len; i12++) {
                                                        int iv12 = all.get(i12);
                                                        // 先清空
                                                        llv.clear();
                                                        llv.add(iv1);
                                                        llv.add(iv2);
                                                        llv.add(iv3);
                                                        llv.add(iv4);
                                                        llv.add(iv5);
                                                        llv.add(iv6);
                                                        llv.add(iv7);
                                                        llv.add(iv8);
                                                        llv.add(iv9);
                                                        llv.add(iv10);
                                                        llv.add(iv11);
                                                        llv.add(iv12);
                                                        // 先清空
                                                        hvMap.clear();
                                                        // 判断是否有效
                                                        for (int hi = 0; hi < llv.size(); hi++) {
                                                            if (hvMap.containsKey(llv.get(hi))) {
                                                                hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                                            } else {
                                                                hvMap.put(llv.get(hi), + 1);
                                                            }
                                                        }
                                                        if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                                            // 牌型不符合规则
                                                            continue;
                                                        }
                                                        for (int i13 = i12; i13 < len; i13++) {
                                                            int iv13 = all.get(i13);
                                                            // 先清空
                                                            llv.clear();
                                                            llv.add(iv1);
                                                            llv.add(iv2);
                                                            llv.add(iv3);
                                                            llv.add(iv4);
                                                            llv.add(iv5);
                                                            llv.add(iv6);
                                                            llv.add(iv7);
                                                            llv.add(iv8);
                                                            llv.add(iv9);
                                                            llv.add(iv10);
                                                            llv.add(iv11);
                                                            llv.add(iv12);
                                                            llv.add(iv13);
                                                            // 先清空
                                                            hvMap.clear();
                                                            // 判断是否有效
                                                            for (int hi = 0; hi < llv.size(); hi++) {
                                                                if (hvMap.containsKey(llv.get(hi))) {
                                                                    hvMap.put(llv.get(hi), hvMap.get(llv.get(hi)) + 1);
                                                                } else {
                                                                    hvMap.put(llv.get(hi), + 1);
                                                                }
                                                            }
                                                            if (hvMap.keySet().stream().anyMatch(v -> hvMap.get(v) >= 5)) {
                                                                // 牌型不符合规则
                                                                continue;
                                                            }
                                                            i++;
                                                            System.out.printf("第几个：%s，值为：%s\n",i,hvMap);
                                                            Set<Integer> huPai1 = algorithm.getHuPai(llv);
                                                            // 有将
                                                            if (huPai1.size() > 0) {
                                                                StringBuilder txt = new StringBuilder();
                                                                List<Integer> integers = new ArrayList<>(huPai1);
                                                                for (int hi = 0; hi < integers.size(); hi++) {
                                                                    txt.append(String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s,%s\n", iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, integers.get(hi)));
                                                                }
                                                                try {
                                                                    BufferedWriter out = new BufferedWriter(new FileWriter("./map/mj-hupai.txt",true));
                                                                    out.write(txt.toString());
                                                                    out.close();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                iu++;
                                                                System.out.printf("%s，胡第几个：%s，值为：%s\n", i, iu, txt);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ll.size());


    }

}

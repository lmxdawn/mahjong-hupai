import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Algorithm {

    /**
     * 计算翻数
     * @return
     */
    public List<Fan> getFan(List<Integer> arr) {

        Set<Integer> haiPaiSet = getHuPai(arr);

        List<Fan> fanList = new ArrayList<>();
        if (haiPaiSet.size() == 0) {
            return fanList;
        }

        haiPaiSet.forEach(v -> {
            List<Integer> arrAll = new ArrayList<>(arr);
            // 追加可以胡牌的到数组
            arrAll.add(v);
            // 出现次数统计
            Map<Integer, Integer> allMap = new HashMap<>();
            // 清一色统计
            Map<Integer, Integer> qingMap = new HashMap<>();
            // 去掉杠的牌
            for (Integer aVal : arrAll) {
                if (allMap.containsKey(aVal)) {
                    allMap.put(aVal, allMap.get(aVal) + 1);
                } else {
                    allMap.put(aVal, 1);
                }
                Integer qingMapK = aVal < 10 ? 0 : (aVal < 20 ? 1 : 2);
                if (qingMap.containsKey(qingMapK)) {
                    qingMap.put(qingMapK, qingMap.get(qingMapK) + 1);
                } else {
                    qingMap.put(qingMapK, 1);
                }
            }

            // 玩家的手牌全部是两张一对的，没有碰过和杠过
            boolean isQiDui = allMap.keySet().stream().noneMatch(vv -> allMap.get(vv) == 1 || allMap.get(vv) == 3);

            // 3个相同的有多少
            long num3 = allMap.keySet().stream().filter(vv -> allMap.get(vv) == 3).count();
            // 4个相同的有多少
            long num4 = allMap.keySet().stream().filter(vv -> allMap.get(vv) == 4).count();

            // 判断是否是清一色
            boolean isQing = qingMap.keySet().size() == 1;

            // 3个相同的和4个相同的有多少
            long num3And4 = num3 + num4;
            // 玩家手牌除了一对对牌以外，剩下的都是三张一对的，一共四对
            boolean isDuiDui = num3And4 == allMap.size() - 1;

            Fan fan = new Fan();
            fan.setHuPai(v);
            fan.setQiDui(isQiDui);
            fan.setNum4(num4);
            fan.setQing(isQing);
            fan.setDuiDui(isDuiDui);

            fanList.add(fan);
        });

        return fanList;
    }

    /**
     * 获取胡牌的
     * @return
     */
    public Set<Integer> getHuPai(List<Integer> hai) {

        Map<Integer, Integer> gangMap = new HashMap<>();
        // 去掉杠的牌
        for (Integer aVal : hai) {
            if (gangMap.containsKey(aVal)) {
                gangMap.put(aVal, gangMap.get(aVal) + 1);
            } else {
                gangMap.put(aVal, 1);
            }
        }

        int len = hai.size();

        Set<Integer>  huPaiSet = new HashSet<>();

        // 判断是否是对子胡
        int duiZiNum = (int) gangMap.keySet().stream().filter(v -> gangMap.get(v) == 1 || gangMap.get(v) == 3).count();

        if (len == 13 && duiZiNum == 1) {
            // gangMap.keySet().forEach(v -> {
            //     if (gangMap.get(v) == 1 || gangMap.get(v) == 3) {
            //         huPaiSet.add(v);
            //     }
            // });
            // 对子胡不用刷表，直接判断
            return huPaiSet;
        }

        int num4 = (int) gangMap.keySet().stream().filter(v -> gangMap.get(v) == 4).count();
        if (num4 >= 1) {
            return huPaiSet;
        }
        Collections.sort(hai);
        List<Map<List<Integer>,List<Integer>>> haiList = new ArrayList<>();

        // 去掉杠的和碰的
        if (len != 1 && len != 4 && len != 7 && len != 10 && len != 13) {
            System.out.println("牌型错误，小相公或者大相公");
            return huPaiSet;
        }

        if (len == 1) {
            huPaiSet.add(hai.get(0));
            return huPaiSet;
        }

        if (len == 4) {
            return getJiang(hai);
        }

        // 把数组取出4个为一组的值
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        List<Integer> tempIndex = new ArrayList<>();
                        List<Integer> tempValue = new ArrayList<>();
                        for (int ii = 0; ii < len; ii++) {
                            Integer hv = hai.get(ii);
                            if (ii == i || ii == j || ii == k || ii == l) {
                                tempIndex.add(hv);
                                continue;
                            }
                            tempValue.add(hv);
                        }
                        Map<List<Integer>,List<Integer>> tempMap = new HashMap<>();
                        tempMap.put(tempIndex, tempValue);
                        haiList.add(tempMap);
                    }
                }
            }
        }

        // 取出4个为一组后
        haiList.forEach(v -> {
            v.keySet().forEach(kv -> {
                List<Integer> integers = v.get(kv);
                // System.out.printf("%s, %s，%s\n", kv, integersList, hh);
                if (isXiaoChu(integers)) {
                    // System.out.printf("消除完，%s，%s\n", kv, integers);
                    Set<Integer> huPai = getJiang(kv);
                    huPaiSet.addAll(huPai);
                }

            });
        });

        return huPaiSet;
    }

    /**
     * 获取将牌（根据4个一组的）
     * @param kv
     * @return
     */
    private Set<Integer> getJiang(List<Integer> kv) {
        int kvLen = kv.size();
        Set<Integer> huPaiSet = new HashSet<>();
        // 判断两个对子
        Map<Integer, Integer> kvMap = new HashMap<>();
        for (int kvi = 0; kvi < kvLen; kvi++) {
            Integer kviV = kv.get(kvi);
            if (kvMap.containsKey(kviV)) {
                kvMap.put(kviV, kvMap.get(kviV) + 1);
            } else {
                kvMap.put(kviV, 1);
            }
        }
        AtomicBoolean hu = new AtomicBoolean(true);
        kvMap.keySet().forEach(kvMapV -> {
            if (kvMap.get(kvMapV) != 2) {
                hu.set(false);
            }
        });
        if (hu.get()) {
            huPaiSet.addAll(kvMap.keySet());
            // System.out.printf("有两个对子，%s，%s", kv, kvMap.keySet());
        } else {
            // 判断有三个相同，或者有三个连续的加一个单牌
            for (int kvi = 0; kvi < kvLen; kvi++) {
                // 踢掉一个判断
                List<Integer> kvListTemp = new ArrayList<>();
                Integer kviTemp = kv.get(kvi);
                for (int kvj = 0; kvj < kvLen; kvj++) {
                    Integer kvjTemp = kv.get(kvj);
                    if (kviTemp.equals(kvjTemp)) {
                        continue;
                    }
                    kvListTemp.add(kvjTemp);
                }
                if (kvListTemp.size() == 3 && isEqualOrContinuous(kvListTemp)) {
                    huPaiSet.add(kviTemp);
                }
            }
            // 判断有两个相同，其它两个是连续的
            // kvMap.keySet().forEach(kvMapV -> {
            //     if (kvMap.get(kvMapV) == 2) {
            //         List<Integer> kvListTemp = new ArrayList<>();
            //         for (Integer kvjTemp : kv) {
            //             if (kvMapV.equals(kvjTemp)) {
            //                 continue;
            //             }
            //             kvListTemp.add(kvjTemp);
            //         }
            //         Collections.sort(kvListTemp);
            //         Integer t0 = kvListTemp.get(0);
            //         Integer t1 = kvListTemp.get(1);
            //         // 判断是否连续
            //         if (t0.equals(t1 - 1)) {
            //             int jiang0 = t0 - 1;
            //             int jiang1 = t1 + 1;
            //             if (jiang0 % 10 == 0) {
            //                 jiang0 = t0;
            //             }
            //             if (jiang1 % 10 == 0) {
            //                 jiang1 = t1;
            //             }
            //             huPaiSet.add(jiang0);
            //             huPaiSet.add(jiang1);
            //         }
            //
            //     }
            // });
            // System.out.printf("可以胡牌，%s，%s", kv, huPaiSet);
        }
        return huPaiSet;
    }

    /**
     * 判断是否能消除
     * @return
     */
    private boolean isXiaoChu(List<Integer> integers) {
        int integersLen = integers.size();
        if (integersLen == 3) {
            return isEqualOrContinuous(integers);
        }
        // 把现在的数组 3个 一起组成
        for (int i = 0; i < integersLen; i++) {
            for (int j = i + 1; j < integersLen; j++) {
                for (int k = j + 1; k < integersLen; k++) {
                    List<Integer> integers1 = new ArrayList<>();
                    integers1.add(integers.get(i));
                    integers1.add(integers.get(j));
                    integers1.add(integers.get(k));
                    if (!isEqualOrContinuous(integers1)) {
                        return false;
                    }
                    List<Integer> vvList = new ArrayList<>();
                    for (int kj = 0; kj < integersLen; kj++) {
                        if (i == kj || j == kj || k == kj) {
                            continue;
                        }
                        vvList.add(integers.get(kj));
                    }

                    boolean xiaoChu = isXiaoChu(vvList);
                    if (xiaoChu) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断是否连续或者相等
     * @param list
     * @return
     */
    private boolean isEqualOrContinuous(List<Integer> list) {
        boolean isLianXu = true;
        boolean isXiangDeng = true;
        for (int vi = 0; vi < list.size() - 1; vi++) {
            Integer vi0 = list.get(vi);
            Integer vi1 = list.get(vi + 1);
            if (vi0 + 1 != vi1) {
                isLianXu = false;
            }
            if (!vi0.equals(vi1)) {
                isXiangDeng = false;
            }
        }
        return isLianXu || isXiangDeng;
    }

    public static class Fan {

        // 要胡的牌
        private Integer huPai;
        // 玩家的手牌全部是两张一对的，没有碰过和杠过
        private Boolean qiDui;
        // 相同的四个数量
        private Long num4;
        // 是否是清一色
        private Boolean qing;
        // 玩家手牌除了一对对牌以外，剩下的都是三张一对的，一共四对
        private Boolean duiDui;

        @Override
        public String toString() {
            return "Fan{" +
                    "huPai=" + huPai +
                    ", qiDui=" + qiDui +
                    ", num4=" + num4 +
                    ", qing=" + qing +
                    ", duiDui=" + duiDui +
                    '}';
        }

        public Boolean getDuiDui() {
            return duiDui;
        }

        public void setDuiDui(Boolean duiDui) {
            this.duiDui = Fan.this.duiDui;
        }

        public Integer getHuPai() {
            return huPai;
        }

        public void setHuPai(Integer huPai) {
            this.huPai = huPai;
        }

        public Boolean getQiDui() {
            return qiDui;
        }

        public void setQiDui(Boolean qiDui) {
            this.qiDui = qiDui;
        }

        public Long getNum4() {
            return num4;
        }

        public void setNum4(Long num4) {
            this.num4 = num4;
        }

        public Boolean getQing() {
            return qing;
        }

        public void setQing(Boolean qing) {
            this.qing = qing;
        }
    }

}

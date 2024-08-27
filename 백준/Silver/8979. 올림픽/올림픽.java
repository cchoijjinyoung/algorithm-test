import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Nation implements Comparable<Nation> {
        private int name;
        private int gold;
        private int silver;
        private int bronze;
        private int rank;

        public Nation(int name, int gold, int silver, int bronze) {
            super();

            this.name = name;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
            this.rank = 1;	// 처음 순위는 1부터 시작
        } // constructor

        // 갯수가 같으면 금, 은, 동 순으로 내려가면서 비교
        @Override
        public int compareTo(Nation o) {

            if(this.gold == o.gold) {
                if(this.silver == o.silver) {
                    return o.bronze - this.bronze;
                } else {
                    return o.silver - this.silver;
                } // if-else
            } else {
                return o.gold - this.gold;
            } // if-else
        } // compareTo

    } // end class:Nation

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Nation> medalList = new ArrayList<>();

        for(int i=1; i<=n; i++) {

            st = new StringTokenizer(br.readLine());

            int name = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            Nation nation = new Nation(name, gold, silver, bronze);
            medalList.add(nation);
        } // for

        Collections.sort(medalList);

        for(int i=1; i<n; i++) {

            // 국가 두개씩 비교해서 rank를 정함
            Nation originN = medalList.get(i-1);
            Nation nextN = medalList.get(i);

            if(originN.gold == nextN.gold &&
                originN.silver == nextN.silver &&
                originN.bronze == nextN.bronze) {

                nextN.rank = originN.rank;
            } else {
                nextN.rank = i + 1;
            } // if-else

        } // for

        medalList.stream().
            filter(t -> t.name == k).
            map(t -> t.rank).
            forEach(System.out::println);
    } // main
} // end class
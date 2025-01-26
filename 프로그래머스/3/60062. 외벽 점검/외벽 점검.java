import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
	static int answer, size, wSize, INF = Integer.MAX_VALUE;
	static int[] dis, fullWeak;
	static Stack<Integer> st;
	static boolean[] check;
	public int solution(int n, int[] weak, int[] dist) {
		wSize = weak.length;
		fullWeak = getFullWeak(n,weak);
		answer=INF;
		dis = dist;
		size = dis.length;
		for(int i=1; i<dist.length+1; i++) {
			st = new Stack<>();
			check = new boolean[size];
			permutation(i);
		}
		return answer == INF ? -1 : answer;
	}

	static void permutation(int r) {
		if(st.size() == r) {
			List<Integer> distCase = new ArrayList<>();
			for(int num : st) {
				distCase.add(num);
			}
			check(distCase);
		}
		
		for(int i=0; i<size; i++) {
			if(!check[i]) {
				check[i] = true;
				st.push(dis[i]);
				permutation(r);
				st.pop();
				check[i] = false;
			}
		}
	}
	
	static void check(List<Integer> distCase) {
		for(int i=0; i<fullWeak.length-wSize+1; i++) {
			int idx=0;
			int cur=0;
			int nxt=0;
			for(cur=i; cur<i+wSize;) {
				nxt = cur+1;
				while(nxt < i+wSize &&
					fullWeak[cur] + distCase.get(idx) >= fullWeak[nxt]) {
					nxt++;
				}
				cur= nxt;
				idx++;
				if(idx == distCase.size()) break;
			}
        	
			if((cur-i)==wSize && idx<answer) {
				answer= idx;
			}
		}
	}
	
	static int[] getFullWeak(int n, int[] weak) {
		int fullSize = weak.length*2-1;
		int[] arr = new int[fullSize];
		for(int i=0; i<weak.length; i++) {
			arr[i] = weak[i];
		}
		
		for(int i=weak.length; i<fullSize; i++) {
			arr[i] =weak[i-weak.length] +n;
		}
		return arr;
	}
}
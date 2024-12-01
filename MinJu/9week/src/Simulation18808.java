import java.io.*;
import java.util.*;

public class Simulation18808 {
    static int n, m, k;
    static boolean[][] note = new boolean[45][45];  //노트북
    static int cnt=0;  //스티커가 붙은 칸의 수
    static Queue<int[][]> queue= new LinkedList<>();  //스티커 저장 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  //노트북의 세로 길이
        m = Integer.parseInt(st.nextToken());  //가로 길이
        k = Integer.parseInt(st.nextToken());  //스티커의 개수

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r= Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());
            int[][] sticker= new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < c; l++) {
                    sticker[j][l]=Integer.parseInt(st.nextToken());
                }
            }
            queue.add(sticker);
        }

        while (!queue.isEmpty()) {  //차례대로 스티커 붙이기
            int[][] sticker = queue.poll();

            boolean isCnt=false;
            //90도씩 돌려서 네 번 반복
            for (int t = 0; t < 4; t++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        isCnt=func(sticker, i, j);  //스티커 대보기
                        if(isCnt) {
                            func2(sticker, i, j);
                            break;
                        }
                    }
                    if(isCnt) break;
                }
                if(isCnt) break;
                sticker = func3(sticker).clone();  //스티커 90도 회전
            }
        }

        System.out.println(cnt);
    }

    public static boolean func(int[][] sticker, int x, int y) {  //sticker를 붙일 수 있는지 확인
        int r = sticker.length;
        int c = sticker[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(x+i>=n || y+j>=m) return false;  //범위 밖
                if(sticker[i][j]==1 && note[x+i][y+j]){  //스티커를 붙일 수 없다.
                    return false;
                }
            }
        }
        return true;
    }

    public static void func2(int[][] sticker, int x, int y) {  //sticker 붙이기
        int r = sticker.length;
        int c = sticker[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(sticker[i][j]==1){  //스티커를 붙일 수 없다.
                    note[x+i][y+j]=true;
                    cnt++;
                }
            }
        }
    }

    public static int[][] func3(int[][] sticker) {
        int r= sticker.length;
        int c= sticker[0].length;
        int[][] newSticker= new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newSticker[j][r-1-i] = sticker[i][j];
            }
        }
        return newSticker;
    }
}

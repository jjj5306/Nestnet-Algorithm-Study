import java.io.*;
import java.util.*;

public class Simulation12100 {
    static int n;
    static int[][] board = new int[20][20];
    static int[][] mainboard = new int[20][20];
    static boolean[][] status = new boolean[20][20];  //합쳐진 상태
    static int maxblock=0;

    //최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {  //2048 칸 채우기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                mainboard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //상하좌우 이동을 5번하고 가장 큰 블록을 찾는다. 4*4*4*4*4
        int i = 0;
        while (i < 1024) {
            for (int j = 0; j < n; j++) {  //이동할 보드 클론
                board[j] = mainboard[j].clone();
            }

            int t=i;
            for (int j = 0; j <= 4; j++) {
                int ti= t%4;
                if (ti == 0) {
                    up();
                }
                if (ti == 1) {
                    down();
                }
                if (ti == 2) {
                    left();
                }
                if (ti == 3) {
                    right();
                }
                for (int l = 0; l < n; l++) {
                    Arrays.fill(status[l], false);  //상태 행렬 초기화
                }

                t/=4;
            }

            i++;
            int max=0;
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if(max<board[j][l]) {
                        max=board[j][l];
                    }
                }
            }
            maxblock=Math.max(maxblock, max);
        }

        System.out.println(maxblock);
    }

    public static void up() {
        for (int j = 0; j < n; j++) {  //열
            for (int i = 1; i < n; i++) {  //행
                if (board[i][j] != 0) {  //블록에 숫자가 써있는 경우
                    int temp = i - 1;  //가장 위에 위치하는 숫자 블록 위치
                    while (temp >= 0) {
                        if (board[temp][j] != 0) {
                            break;
                        }
                        temp--;
                    }
                    if(temp==i-1&& board[temp][j]!=board[i][j]) continue;  //아무일도 일어나지 않는 경우

                    if (temp!=-1 && !status[temp][j] && board[temp][j] == board[i][j]) {  //블록이 합쳐지는 경우
                        board[temp][j] += board[i][j];
                        status[temp][j] = true;
                        board[i][j] = 0;
                        continue;
                    }

                    //블록이 합쳐지지 않는 경우 : 이동만
                    board[temp + 1][j] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void down() {
        for (int j = 0; j <n; j++) {  //열
            for (int i = n-2; i >=0; i--) {  //행
                if (board[i][j] != 0) {  //블록에 숫자가 써있는 경우
                    int temp = i +1;  //가장 아래 숫자 블록 위치
                    while (temp <n) {
                        if (board[temp][j] != 0) {
                            break;
                        }
                        temp++;
                    }
                    if(temp==i+1&& board[temp][j]!=board[i][j]) continue;  //아무일도 일어나지 않는 경우

                    if (temp!=n && !status[temp][j] && board[temp][j] == board[i][j]) {  //블록이 합쳐지는 경우
                        board[temp][j] += board[i][j];
                        status[temp][j] = true;
                        board[i][j] = 0;
                        continue;
                    }

                    //블록이 합쳐지지 않는 경우 : 이동만
                    board[temp - 1][j] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void left() {
        for (int i = 0; i < n; i++) {  //행
            for (int j = 1; j < n; j++) {  //열
                if (board[i][j] != 0) {  //블록에 숫자가 써있는 경우
                    int temp = j - 1;  //가장 왼쪽에 위치하는 숫자 블록 위치
                    while (temp >= 0) {
                        if (board[i][temp] != 0) {
                            break;
                        }
                        temp--;
                    }
                    if(temp==j-1&& board[i][temp]!=board[i][j]) continue;  //아무일도 일어나지 않는 경우

                    if (temp!=-1 && !status[i][temp] && board[i][temp] == board[i][j]) {  //블록이 합쳐지는 경우
                        board[i][temp] += board[i][j];
                        status[i][temp] = true;
                        board[i][j] = 0;
                        continue;
                    }

                    //블록이 합쳐지지 않는 경우 : 이동만
                    board[i][temp+1] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void right() {
        for (int i = 0; i < n; i++) {  //행
            for (int j = n-2; j >=0; j--) {  //열
                if (board[i][j] != 0) {  //블록에 숫자가 써있는 경우
                    int temp = j + 1;  //가장 오른쪽에 위치하는 숫자 블록 위치
                    while (temp <n) {
                        if (board[i][temp] != 0) {
                            break;
                        }
                        temp++;
                    }
                    if(temp==j+1&& board[i][temp]!=board[i][j]) continue;  //아무일도 일어나지 않는 경우

                    if (temp!=n && !status[i][temp] && board[i][temp] == board[i][j]) {  //블록이 합쳐지는 경우
                        board[i][temp] += board[i][j];
                        status[i][temp] = true;
                        board[i][j] = 0;
                        continue;
                    }

                    //블록이 합쳐지지 않는 경우 : 이동만
                    board[i][temp-1] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.se.ttg.game;

/**
 *
 * @author jshpr
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YachuDiceGame {

    private static ArrayList<Integer> rolled = new ArrayList<>();
    private static ArrayList<Integer> selected = new ArrayList<>();
    private static ArrayList<String> b = new ArrayList<>(16);
    private static int[] bint = new int[16];
    private static int rollcnt = 3;

    public static void main(String[] args) {
        initialize();
        playGame();
    }

    private static void initialize() {
        for (int i = 0; i < bint.length; i++) {
            bint[i] = 0;
        }
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (rollcnt > 0) {
            System.out.println("주사위 던지기 (남은 횟수 : " + rollcnt + ")");
            rollDice();
            System.out.println("계속 하시겠습니까? (y/n)");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                break;
            }
        }
        scanner.close();
    }

    private static void rollDice() {
        rollcnt--;
        rolled.clear();
        Random random = new Random();
        for (int i = 0; i < 5 - selected.size(); i++) {
            int ran = random.nextInt(6);
            rolled.add(ran);
        }
        setDices();
    }
    
    private static String getDiceImageUrl(int diceNumber) {
    // 주사위 이미지 파일의 상대 경로
    String basePath = "images/";
    // 주사위 번호에 해당하는 이미지 파일 이름
    String imageName = "dice_" + diceNumber + ".jpg";
    // 이미지 파일의 전체 경로
    String imageUrl = basePath + imageName;
    return imageUrl;
}

    private static void setDices() {
        for (int i = 0; i < selected.size(); i++) {
        int diceNumber = selected.get(i);
        // 주사위 번호에 해당하는 이미지 파일의 URL 가져오기
        String imageUrl = getDiceImageUrl(diceNumber);
        // 이미지 URL을 사용하여 주사위를 표시하는 코드를 여기에 추가
    }
    }

    private static void diceSelect(int clicked_num) {
        String clicked_img = getDiceImageUrl(clicked_num);
        selected.add(Integer.parseInt(clicked_img.substring(10)));
        rolled.remove(clicked_num);
        setDices();
        setScores(selected, b, bint);
    }

    private static void diceUnSelect(int clicked_num) {
        String clicked_img = getDiceImageUrl(clicked_num);
        rolled.add(Integer.parseInt(clicked_img.substring(10)));
        selected.remove(clicked_num);
        setDices();
        setScores(selected, b, bint);
    }

    public static void setScores(ArrayList<Integer> selected, ArrayList<String> b, int[] bint) {
        int[] selectedDiceCounts = new int[6];
        Arrays.fill(selectedDiceCounts, 0);

        for (int num : selected) {
            selectedDiceCounts[num]++;
        }

        for (int i = 1; i < 16; i++) {
            if (Integer.parseInt(b.get(i)) == 0) {
                continue;
            }
            b.set(i, "0");
            bint[i] = 0;
        }

        // Aces
        if (selectedDiceCounts[0] > 0 && Integer.parseInt(b.get(1)) != 0) {
            bint[1] = Integer.parseInt(b.get(1)) * selectedDiceCounts[0];
        }

        // Twos
        if (selectedDiceCounts[1] > 0 && Integer.parseInt(b.get(2)) != 0) {
            bint[2] = Integer.parseInt(b.get(2)) * (selectedDiceCounts[1] * 2);
        }

        // Threes
        if (selectedDiceCounts[2] > 0 && Integer.parseInt(b.get(3)) != 0) {
            bint[3] = Integer.parseInt(b.get(3)) * (selectedDiceCounts[2] * 3);
        }

        // Fours
        if (selectedDiceCounts[3] > 0 && Integer.parseInt(b.get(4)) != 0) {
            bint[4] = Integer.parseInt(b.get(4)) * (selectedDiceCounts[3] * 4);
        }

        // Fives
        if (selectedDiceCounts[4] > 0 && Integer.parseInt(b.get(5)) != 0) {
            bint[5] = Integer.parseInt(b.get(5)) * (selectedDiceCounts[4] * 5);
        }

        // Sixes
        if (selectedDiceCounts[5] > 0 && Integer.parseInt(b.get(6)) != 0) {
            bint[6] = Integer.parseInt(b.get(6)) * (selectedDiceCounts[5] * 6);
        }

        // Mission
        b.set(7, bint[7] + "/63");

        // Bonus
        b.set(8, String.valueOf(bint[8]));

        // Chance
        if (bint[9] != 0) {
            int sum = 0;
            for (int i = 0; i < 6; i++) {
                if (selectedDiceCounts[i] > 0) {
                    sum += selectedDiceCounts[i] * (i + 1);
                }
            }
            if (sum > 0) {
                b.set(9, String.valueOf(sum));
            }
        }

        // Four of a Kind
        if (bint[10] != 0) {
            int tmp1 = 0, tmp4 = 0;
            for (int i = 0; i < 6; i++) {
                if (selectedDiceCounts[i] == 1) {
                    tmp1 = i + 1;
                }

                if (selectedDiceCounts[i] == 4) {
                    tmp4 = (i + 1) * 4;
                }
                if (selectedDiceCounts[i] == 5) {
                    tmp4 = (i + 1) * 5;
                }
            }
            if (tmp4 > 0) {
                b.set(10, String.valueOf(tmp4 + tmp1));
            }
        }

        // Full House
        int isPair = 0, isTriple = 0;
        if (bint[11] != 0) {
            for (int i = 0; i < 6; i++) {
                if (selectedDiceCounts[i] == 2) {
                    isPair = i + 1;
                }
                if (selectedDiceCounts[i] == 3) {
                    isTriple = i + 1;
                }
            }
            if (isPair > 0 && isTriple > 0) {
                b.set(11, String.valueOf(isPair * 2 + isTriple * 3));
            }
        }

        // Small Straight
        if (bint[12] != 0) {
            if (selectedDiceCounts[0] > 0 && selectedDiceCounts[1] > 0 && selectedDiceCounts[2] > 0 && selectedDiceCounts[3] > 0 && selectedDiceCounts[4] > 0) {
                b.set(12, "30");
            }
        }

        // Large Straight
        if (bint[13] != 0) {
            if (selectedDiceCounts[1] > 0 && selectedDiceCounts[2] > 0 && selectedDiceCounts[3] > 0 && selectedDiceCounts[4] > 0 && selectedDiceCounts[5] > 0) {
                b.set(13, "40");
            }
        }

        // Yacht
        if (bint[14] != 0) {
            for (int i = 0; i < 6; i++) {
                if (selectedDiceCounts[i] == 5) {
                    b.set(14, "50");
                }
            }
        }

        // Total Score
        b.set(15, String.valueOf(bint[15]));
    }

    public static void addScore(String clickedId, ArrayList<String> b, int[] bint) {
        int clickedNum = Integer.parseInt(clickedId.substring(1));
        int clickedIndex = Integer.parseInt(clickedId) - 1;

        if (b.get(clickedIndex) == null || b.get(clickedIndex).equals("")) {
            return; // 수정된 부분
        }
        if (1 <= clickedNum && clickedNum <= 6 && b.get(clickedIndex) != null && !b.get(clickedIndex).equals("")) { // 수정된 부분
            bint[7] += bint[clickedIndex];
            b.set(7, bint[7] + "/63");

            bint[15] += bint[clickedIndex];
            b.set(15, String.valueOf(bint[15]));

            if (bint[7] >= 63 && bint[8] == 0) {
                bint[8] = 35;
                b.set(8, String.valueOf(bint[8]));

                bint[15] += bint[8];
                b.set(15, String.valueOf(bint[15]));
            }
        } else if (9 <= clickedNum && clickedNum <= 14 && b.get(clickedIndex) != null && !b.get(clickedIndex).equals("")) { // 수정된 부분
            bint[15] += bint[clickedIndex];
            b.set(15, String.valueOf(bint[15]));
        }

        b.set(clickedIndex, ""); // 해당 카테고리 점수 초기화
        resetDiceBoard(); // 주사위 보드 재설정
    }

    private static void resetDiceBoard() {
        for (int i = 1; i < 16; i++) {
            bint[i] = 0;
        }
        rolled.clear();
        selected.clear();
        rollcnt = 3;
    }

    private static void restartGame() {
        System.out.println("게임이 끝났습니다. 점수 : " + bint[15]);
        resetDiceBoard();
        playGame();
    }
}

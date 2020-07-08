package info.manxi.tree;

/**
 * desc
 *
 * @author sheldon 80752866
 * @project java14-101
 * @since 2020/7/8
 */
public class TestCase {
    public static void main(String[] args) {
//        test1();
//        System.out.println("======insertToLeft");
//        insertToLeft();
//        System.out.println("======insertToLeftMiddle");
//        insertToLeftMiddle();
//        System.out.println("======insertToMiddle");
//        insertToMiddle();
//        System.out.println("======insertToMidMiddle");
//        insertToMidMiddle();
//        System.out.println("======insertToMidRight");
//        insertToMidRight();
//        System.out.println("======insertToRight");
//        insertToRight();
//        System.out.println("======insertToRightLeft");
//        insertToRightLeft();
//        System.out.println("======insertToRightMiddle");
//        insertToRightMiddle();
//        System.out.println("======insertToRightUpper");
//        insertToRightUpper();
//        System.out.println("======insertToRightUpper2");
//        insertToLeftUpper2();
//        System.out.println("======insertToRightUpper3");
//        insertToLeftUpper3();
//        System.out.println("======insertToRightUpper4");
//        insertToLeftUpper4();
//        System.out.println("======insertToRightUpper4");
//        insertToRightUpper2();
        System.out.println("======insertToRightUpper4");
        insertToRightUpper4();
    }

    private static void test1() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(190, null);
        tree.entry(200);
        tree.entry(50);
        tree.entry(100);
        tree.entry(191);
        tree.entry(192);
        tree.entry(6);
        tree.entry(7);
        tree.printTree();
    }

    private static void insertToLeft() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(30);//left
        tree.printTree();
    }

    private static void insertToLeftMiddle() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(60);//middle
        tree.printTree();
    }

    private static void insertToMiddle() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);//middle
        tree.printTree();
    }

    private static void insertToMidMiddle() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);//middle
        tree.entry(180);
        tree.entry(160);//middle
        tree.printTree();
    }

    private static void insertToMidRight() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);//middle
        tree.entry(160);
        tree.entry(180);//right
        tree.printTree();
    }

    private static void insertToRight() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);
        tree.entry(160);
        tree.entry(280);
        tree.entry(380);//right
        tree.printTree();
    }

    private static void insertToRightLeft() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);
        tree.entry(160);
        tree.entry(280);
        tree.entry(380);//right
        tree.entry(370);//left
        tree.printTree();
    }

    private static void insertToRightMiddle() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);
        tree.entry(160);
        tree.entry(280);
        tree.entry(380);//right
        tree.entry(370);//left
        tree.entry(375);//middle
        tree.printTree();
    }

    private static void insertToRightUpper() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(100);
        tree.entry(150);
        tree.entry(160);
        tree.entry(280);
        tree.entry(380);//right
        tree.entry(370);//left
        tree.entry(375);
        tree.entry(372);
        tree.entry(373);
        tree.printTree();
    }

    private static void insertToLeftUpper2() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(60);
        tree.entry(220);
        tree.entry(280);
        tree.entry(350);
        tree.entry(380);
        tree.entry(70);
        tree.entry(370);
        tree.entry(320);
        tree.entry(58);
        tree.entry(55);
        tree.printTree();
    }

    private static void insertToLeftUpper3() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(60);
        tree.entry(220);
        tree.entry(280);
        tree.entry(350);
        tree.entry(380);
        tree.entry(70);
        tree.entry(370);
        tree.entry(320);
        tree.entry(58);
        tree.entry(62);
        tree.entry(63);
        tree.printTree();
    }

    private static void insertToLeftUpper4() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(60);
        tree.entry(220);
        tree.entry(280);
        tree.entry(350);
        tree.entry(380);
        tree.entry(70);
        tree.entry(370);
        tree.entry(320);
        tree.entry(58);
        tree.entry(210);
        tree.entry(215);
        tree.printTree();
    }

    private static void insertToRightUpper2() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(60);
        tree.entry(220);
        tree.entry(280);
        tree.entry(350);
        tree.entry(380);
        tree.entry(70);
        tree.entry(370);
        tree.entry(320);
        tree.entry(58);
        tree.entry(210);
        tree.entry(215);
        tree.entry(360);
        tree.printTree();
    }

    private static void insertToRightUpper4() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>(200, null);
        tree.entry(300);
        tree.entry(50);
        tree.entry(60);
        tree.entry(220);
        tree.entry(280);
        tree.entry(350);
        tree.entry(380);
        tree.entry(70);
        tree.entry(370);
        tree.entry(320);
        tree.entry(58);
        tree.entry(210);
        tree.entry(215);
        tree.entry(360);
        tree.entry(368);
        tree.entry(365);
        tree.printTree();
    }
}

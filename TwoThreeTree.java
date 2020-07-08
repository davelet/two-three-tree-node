package info.manxi.tree;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 2-3树
 *
 * @author sheldon 80752866
 * @project java14-101
 * @since 2020/7/7
 */
public class TwoThreeTree<K extends Comparable<K>> {
    private final LinkedList<K> keys;//节点key，最多3个元素

    private final LinkedList<TwoThreeTree<K>> pointers;
    private TwoThreeTree<K> parent;

    public TwoThreeTree(K k, TwoThreeTree<K> parent) {
        this.keys = new LinkedList<>();
        this.pointers = new LinkedList<>();
        keys.add(k);
        this.parent = parent;
    }

    /**
     * 打印当前节点和直系亲属
     *
     * @return 当前节点和直系亲属
     */
    @Override
    public String toString() {
        return "TwoThreeTree{" +
                keys +
                "=>" + pointers.stream().map(j -> j.keys).collect(Collectors.toList()) +
                '}';
    }

    public void printTree() {
        TwoThreeTree<K> o = this;
        while (o.parent != null) {
            o = o.parent;
        }
        printTreeNode(o);
    }
    private void printTreeNode(TwoThreeTree<K> o) {
        System.out.println(o);
        if (!o.pointers.isEmpty()) {
            o.pointers.forEach(this::printTreeNode);
        }
    }

    public void entry(K k) {
        TwoThreeTree<K> o = this;
        while (o.parent != null) {
            o = o.parent;
        }
        o.add(k);
    }

    private synchronized void add(K k) {
        if (keys.size() == 1) {//2-node
            if (keys.getFirst().equals(k)) {
                return;
            }
            if (keys.getFirst().compareTo(k) > 0) {// to left
                if (pointers.isEmpty()) {
                    keys.addFirst(k);// add as first key
                } else {
                    pointers.getFirst().add(k); // proceed in left sub tree
                }
            } else { // to right
                if (pointers.isEmpty() || pointers.size() == 1) {
                    keys.addLast(k);// add as last key
                } else {
                    pointers.getLast().add(k); // proceed in right sub tree
                }
            }
        } else if (keys.size() == 2) {//3-node
            if (keys.getFirst().equals(k) || keys.getLast().equals(k)) {
                return;
            }
            K first = keys.getFirst();
            K second = keys.getLast();
            if (first.compareTo(k) > 0) {// to left
                if (pointers.isEmpty()) {
                    keys.addFirst(k);//  have to change structure
                    promoteLeft();
                } else {
                    pointers.getFirst().add(k);
                }
            } else if (second.compareTo(k) < 0) {// to right
                if (pointers.isEmpty()) {
                    keys.addLast(k);//  have to change structure
                    promoteRight();
                } else {
                    pointers.getLast().add(k);
                }
            } else { // to middle
                if (pointers.size() > 1) {
                    pointers.get(1).add(k);
                } else {
                    keys.add(1, k);
                    promoteMiddle();
                }
            }
        } else {
            throw new RuntimeException("corrupted tree");
        }
    }

    private void promoteMiddle() {
        if (keys.size() < 3) {
            return;
        }

        K first = keys.pollFirst();
        K mid = keys.pollFirst();
        K last = keys.getFirst();
        if (first == null || last == null || mid == null) {
            throw new RuntimeException("corrupted tree");
        }
        if (parent == null) {// the root
            TwoThreeTree<K> newRoot = new TwoThreeTree<>(mid, null);
            TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, newRoot);
            newRoot.pointers.add(newLeft);
            if (!pointers.isEmpty()) {
                newLeft.pointers.add(pointers.pollFirst());
                newLeft.pointers.getFirst().parent = newLeft;
            }
            if (!pointers.isEmpty()) {
                newLeft.pointers.add(pointers.pollFirst());
                newLeft.pointers.getFirst().parent = newLeft;
            }
            newRoot.pointers.add(this);
            this.parent = newRoot;
        } else {
            K pf = parent.keys.getFirst();
            if (parent.keys.size() == 1) {
                if (pf.compareTo(mid) > 0) { //  from left sub
                    parent.keys.addFirst(mid);
                    TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
                    if (!pointers.isEmpty()) {
                        newLeft.pointers.add(pointers.pollFirst());
                        newLeft.pointers.getFirst().parent = newLeft;
                    }
                    if (!pointers.isEmpty()) {
                        newLeft.pointers.add(pointers.pollFirst());
                        newLeft.pointers.getFirst().parent = newLeft;
                    }
                    parent.pointers.addFirst(newLeft);
                    parent.promoteLeft();// 递归处理
                } else {
                    parent.keys.addLast(mid);
                    TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
                    if (!pointers.isEmpty()) {
                        newLeft.pointers.add(pointers.pollFirst());
                        newLeft.pointers.getFirst().parent = newLeft;
                    }
                    if (!pointers.isEmpty()) {
                        newLeft.pointers.add(pointers.pollFirst());
                        newLeft.pointers.getFirst().parent = newLeft;
                    }
                    parent.pointers.add(1, newLeft);
                    parent.promoteRight();// 递归处理
                }
            } else {
                K pl = parent.keys.getLast();
                if (pf.compareTo(mid) > 0) { //  from left sub
                    parent.keys.addFirst(mid);
                    TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
//                    if (!pointers.isEmpty()) {
//                        newLeft.pointers.add(pointers.pollFirst());
//                        newLeft.pointers.getFirst().parent = newLeft;
//                    }
//                    if (!pointers.isEmpty()) {
//                        newLeft.pointers.add(pointers.pollFirst());
//                        newLeft.pointers.getFirst().parent = newLeft;
//                    }
                    parent.pointers.addFirst(newLeft);
                    parent.promoteLeft();// 递归处理
                } else if (pl.compareTo(mid) < 0) {
                    parent.keys.addLast(mid);
//                parent.promoteRight();

                    TwoThreeTree<K> newRight = new TwoThreeTree<>(first, parent);
                    if (!pointers.isEmpty()) {
                        newRight.pointers.addLast(pointers.pollFirst());
                        newRight.pointers.getLast().parent = newRight;
                    }
                    if (!pointers.isEmpty()) {
                        newRight.pointers.addLast(pointers.pollFirst());
                        newRight.pointers.getLast().parent = newRight;
                    }
                    parent.pointers.add(2, newRight);
                    parent.promoteRight();// 递归处理
                } else {
                    parent.keys.add(1, mid);
                    TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);

                    parent.pointers.add(1, newLeft);
                    parent.promoteMiddle();// 递归处理
                }
            }
        }
    }

    /**
     * 变成了4-node，需要自下而上变更
     */
    private void promoteLeft() {
        if (keys.size() < 3) {
            return;
        }

        K first = keys.pollFirst(); // remove the first key
        K mid = keys.pollFirst(); // remove the second key
        if (first == null || mid == null) {
            throw new RuntimeException("corrupted tree");
        }
        if (parent == null) {// the root
            TwoThreeTree<K> newRoot = new TwoThreeTree<>(mid, null);
            TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, newRoot);
            newRoot.pointers.add(newLeft);
            if (!pointers.isEmpty()) {
                newLeft.pointers.add(pointers.pollFirst());
                newLeft.pointers.getFirst().parent = newLeft;
            }
            if (!pointers.isEmpty()) {
                newLeft.pointers.add(pointers.pollFirst());
                newLeft.pointers.getFirst().parent = newLeft;
            }
            newRoot.pointers.add(this);
            this.parent = newRoot;
        } else {
            K pf = parent.keys.getFirst();
            K ps = parent.keys.getLast();
            if (pf.compareTo(mid) > 0) { //  from left sub, always true
                parent.keys.addFirst(mid);
                TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
                if (!pointers.isEmpty()) {
                    newLeft.pointers.add(pointers.pollFirst());
                    newLeft.pointers.getFirst().parent = newLeft;
                }
                if (!pointers.isEmpty()) {
                    newLeft.pointers.add(pointers.pollFirst());
                    newLeft.pointers.getFirst().parent = newLeft;
                }
                parent.pointers.addFirst(newLeft);
                parent.promoteLeft();// 递归处理
            } else if (ps.compareTo(mid) < 0) {
                parent.keys.addLast(mid);
                TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
                if (!pointers.isEmpty()) {
                    newLeft.pointers.add(pointers.pollFirst());
                    newLeft.pointers.getFirst().parent = newLeft;
                }
                if (!pointers.isEmpty()) {
                    newLeft.pointers.add(pointers.pollFirst());
                    newLeft.pointers.getFirst().parent = newLeft;
                }
                parent.pointers.add(1, newLeft);
                parent.promoteRight();// 递归处理
            } else {
                parent.keys.add(1, mid);
                TwoThreeTree<K> newLeft = new TwoThreeTree<>(first, parent);
//                if (!pointers.isEmpty()) {
//                    newLeft.pointers.add(pointers.pollFirst());
//                    newLeft.pointers.getFirst().parent = newLeft;
//                }
//                if (!pointers.isEmpty()) {
//                    newLeft.pointers.add(pointers.pollFirst());
//                    newLeft.pointers.getFirst().parent = newLeft;
//                }
                parent.pointers.add(2, newLeft);
                parent.promoteMiddle();// 递归处理
            }
        }
    }

    private void promoteRight() {
        if (keys.size() < 3) {
            return;
        }

//        K first = keys.getFirst(); // left the first key
        K second = keys.pollLast(); // remove the last one
        K mid = keys.pollLast(); // remove the second key
        if (second == null || mid == null) {
            throw new RuntimeException("corrupted tree");
        }
        if (parent == null) {// the root
            TwoThreeTree<K> newRoot = new TwoThreeTree<>(mid, null);
            TwoThreeTree<K> newRight = new TwoThreeTree<>(second, newRoot);
            newRoot.pointers.add(this);
            this.parent = newRoot;
            newRoot.pointers.add(newRight);
            if (pointers.size() > 2) {
                newRight.pointers.addLast(pointers.remove(2));
                newRight.pointers.getLast().parent = newRight;
            }
            if (pointers.size() > 2) {
                newRight.pointers.addLast(pointers.remove(2));
                newRight.pointers.getLast().parent = newRight;
            }
        } else {
            K pf = parent.keys.getFirst();
            K ps = parent.keys.getLast();
            if (pf.compareTo(mid) > 0) { // from left sub
                parent.keys.addFirst(mid);
                TwoThreeTree<K> newRight = new TwoThreeTree<>(second, parent);
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                parent.pointers.add(1, newRight);
                parent.promoteLeft();// 递归处理
            } else if (ps.compareTo(mid) < 0) {
                parent.keys.addLast(mid);
//                parent.promoteRight();

                TwoThreeTree<K> newRight = new TwoThreeTree<>(second, parent);
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                parent.pointers.addLast(newRight);
                parent.promoteRight();// 递归处理
            } else {
                parent.keys.add(1, mid);
                TwoThreeTree<K> newRight = new TwoThreeTree<>(second, parent);
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                if (pointers.size() > 2) {
                    newRight.pointers.addLast(pointers.remove(2));
                    newRight.pointers.getLast().parent = newRight;
                }
                parent.pointers.add(2, newRight);
                parent.promoteMiddle();// 递归处理
            }
        }
    }
}

package info.manxi.tree;

import java.util.LinkedList;

/**
 * desc
 *
 * @author sheldon 80752866
 * @project java14-101
 * @since 2020/7/7
 */
public class IndexedKeyNode<K extends Comparable<K>> extends LinkedList<KeyNode<K>> {
    @Override
    public KeyNode<K> get(int index) {
        return super.get(index);
    }
}

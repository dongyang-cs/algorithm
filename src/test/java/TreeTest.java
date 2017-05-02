import me.alfod.datastructure.tree.AVLTree;
import me.alfod.utils.Print;
import org.junit.Test;

public class TreeTest {
    @Test
    public void AVLTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.add(5);
        avlTree.add(6);
        avlTree.add(7);
        avlTree.add(8);
        avlTree.add(9);
        Print.print(avlTree.getRoot());
        avlTree.del(4);
        Print.print(avlTree.getRoot());
    }
}

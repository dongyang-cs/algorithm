import me.alfod.datastructure.tree.AVLTree;
import me.alfod.datastructure.tree.RBTree;
import me.alfod.datastructure.tree.SplayTree;
import me.alfod.datastructure.tree.Tree;
import me.alfod.utils.Print;
import org.junit.Test;

public class TreeTest {
    @Test
    public void avlTest() {
        Tree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add(i);
            //Print.print(tree.getRoot());
            //Print.println(Print.getDuplicateString('=',49));
        }
        Print.print(tree.getRoot());
        tree.del(5);
        Print.print(tree.getRoot());
    }

    @Test
    public void RBTreeTest() {
        Tree<Integer> tree = new RBTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add(i);
            //Print.print(tree.getRoot());
            //Print.println(Print.getDuplicateString('=',49));
        }
        Print.print(tree.getRoot());
        tree.del(5);
        Print.print(tree.getRoot());
    }

    @Test
    public void SplayTreeTest() {
        Tree<Integer> tree = new SplayTree<>();
        for (int i = 0; i < 5; i++) {
            tree.add(i);
            //Print.print(tree.getRoot());
            //Print.println(Print.getDuplicateString('=',49));
        }
        Print.print(tree.getRoot());
        for (int i = 0; i < 3; i++) {
            tree.contain(4);
        }
        Print.print(tree.getRoot());
    }

    @Test
    public void hash() {

    }
}

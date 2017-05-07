import me.alfod.datastructure.tree.RBTree;
import me.alfod.datastructure.tree.Tree;
import me.alfod.utils.Print;
import org.junit.Test;

public class TreeTest {
    @Test
    public void AVLTest() {
        Tree<Integer> tree = new RBTree<>();
        for (int i = 0; i < 15; i++) {
            tree.add(i);
            Print.print(tree.getRoot());
            Print.println(Print.getDuplicateString('=',49));
        }
        Print.print(tree.getRoot());
        //tree.del(4);
       // Print.print(tree.getRoot());
    }
}

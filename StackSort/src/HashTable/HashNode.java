package HashTable;

/**
 * Created by Administrator on 2017/2/1.
 */
public class HashNode<Key, Value> {
    public HashNode<Key, Value> next;
    public Key key;
    public Value value;
    private boolean active = true;

    public HashNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void delete() {
        setActive(false);
    }

    public void active() {
        setActive(true);
    }

    @Override
    public String toString() {
        return "TreeNode{ key= " + key +
                ", value= " + value +
                ", active= " + active + " }";
    }
}

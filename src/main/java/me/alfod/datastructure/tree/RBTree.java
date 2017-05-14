package me.alfod.datastructure.tree;

import me.alfod.datastructure.tree.TreeNode.Color;
import me.alfod.utils.Print;

public class RBTree<V extends Comparable<? super V>> extends BaseTree<V> {


    @Override
    public void add(final V v) {
        if (root == null) {
            root = new TreeNode<>(v, Color.BLACK);
            return;
        }
        TreeNode<V> node = root;
        TreeNode<V> valueNode = new TreeNode<>(v, Color.RED);
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                    continue;
                }
                node.setRight(valueNode);
                if (node.getColor() == Color.RED) {
                    colorCheck(valueNode);
                }
                return;
            }
            if (v.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    continue;
                }
                node.setLeft(valueNode);
                if (node.getColor() == Color.RED) {
                    colorCheck(valueNode);
                }
                return;
            }
            return;
        }

    }

    private void colorCheck(TreeNode<V> node) {
        if (node == null || node.getColor() == Color.BLACK) {
            return;
        }
        Print.println(node);
        TreeNode<V> parent = node.getParent();
        if (parent == null) {
            Print.println("parent null");
            node.setColor(Color.BLACK);
            root = node;
            return;
        }
        if (parent.getColor() == Color.BLACK) {
            return;
        }
        TreeNode<V> grandPa = parent.getParent();
        if (grandPa == null) {
            parent.setColor(Color.BLACK);
            root = parent;
            return;
        }
        if (parent.isRightOfParent()) {
            TreeNode<V> uncle = grandPa.getLeft();
            if (node.isRightOfParent()) {
                if (grandPa.getLeft() == null) {
                    Print.println("right right left_null");
                    rotateLeft(grandPa);
                    grandPa.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    colorCheck(parent);
                    return;
                }
                if (grandPa.getLeft().getColor() == Color.RED) {
                    Print.println("right right left_red");
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                    return;
                }
                if (grandPa.getLeft().getColor() == Color.BLACK) {
                    Print.println("right right left_black");
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    rotateLeft(grandPa);

                }
            } else {
                if (uncle == null || uncle.getColor() == Color.BLACK) {
                    Print.println("right left left_null");
                    if (grandPa.getParent() != null) {
                        if (grandPa.isRightOfParent()) {
                            grandPa.getParent().setRight(node);
                        } else {
                            grandPa.getParent().setLeft(node);
                        }
                    } else {
                        root = node;
                    }
                    parent.setLeft(node.getRight());
                    grandPa.setRight(node.getLeft());
                    node.setRight(parent);
                    node.setLeft(grandPa);
                    node.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);

                } else {
                    uncle.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                }

            }
        } else {

            TreeNode<V> uncle = grandPa.getRight();
            if (node.isRightOfParent()) {
                if (uncle == null || uncle.getColor() == Color.BLACK) {
                    Print.println("left right ");
                    if (grandPa.getParent() != null) {
                        if (grandPa.isRightOfParent()) {
                            grandPa.getParent().setRight(node);
                        } else {
                            grandPa.getParent().setLeft(node);
                        }
                    } else {
                        root = node;
                    }
                    parent.setRight(node.getLeft());
                    grandPa.setLeft(node.getRight());
                    node.setLeft(parent);
                    node.setRight(grandPa);
                    node.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                } else {
                    uncle.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                }
            } else {

                if (grandPa.getRight() == null) {
                    Print.println("left left right_null");
                    rotateRight(grandPa);
                    grandPa.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    return;
                }
                if (grandPa.getRight().getColor() == Color.RED) {
                    Print.println("left left right_red");
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                    return;
                }
                if (grandPa.getRight().getColor() == Color.BLACK) {
                    Print.println("left left right_black");
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    rotateRight(grandPa);
                }
            }
        }
    }

    @Override
    public void del(V v) {
        TreeNode<V> node = root;
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                }
            } else if (v.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                }
            } else {
                //v is equal to node.getValue()
                if (node.getLeft() != null) {
                    while (node.getRight() != null) {
                        node = node.getRight();
                    }
                    deleteNode(node);
                    return;
                } else if (node.getRight() != null) {
                    while (node.getLeft() != null) {
                        node = node.getLeft();
                    }
                    deleteNode(node);
                    return;
                } else {
                    deleteNode(node);
                    return;
                }
            }
        }
    }

    private void deleteNode(TreeNode<V> node) {
        if (node == null) {
            return;
        } else if (node.getColor() == Color.RED) {
            node.delete();
        } else if (node.getParent() == null) {
            root = null;
        } else if (node.getParent().getColor() == Color.RED) {
            TreeNode<V> parent = node.getParent();
            if (node.getRight() != null) {
                node.getRight().setColor(Color.BLACK);
                if (node.getLeft() != null) {
                    node.getRight().setLeft(node.getLeft());
                }
                parent.replace(node, node.getRight());
            } else if (node.getLeft() != null) {
                node.getLeft().setColor(Color.BLACK);
                parent.replace(node, node.getLeft());
            } else if (node.isRightOfParent()) {
                //brother is on left side of parent
                TreeNode<V> brother = node.getBrother();
                if (brother.getLeft() != null) {
                    parent.setColor(Color.BLACK);
                    brother.setColor(Color.RED);
                    brother.getLeft().setColor(Color.BLACK);
                    rotateRight(parent);
                } else if (brother.getRight() != null) {
                    parent.getRight().setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    doubleRotateRight(parent);
                } else {
                    parent.setColor(Color.BLACK);
                    brother.setColor(Color.RED);
                }
            } else {
                //brother is on right side of parent
                //parent is red
                //both node and brother is black
                TreeNode<V> brother = node.getBrother();
                if (brother.getRight() != null) {
                    parent.setColor(Color.BLACK);
                    brother.setColor(Color.RED);
                    brother.getRight().setColor(Color.BLACK);
                    rotateLeft(parent);
                } else if (brother.getLeft() != null) {
                    parent.getLeft().setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    doubleRotateLeft(parent);
                } else {
                    parent.setColor(Color.BLACK);
                    brother.setColor(Color.RED);
                }
            }
            node.delete();
        } else if (node.getParent().getColor() == Color.BLACK) {
            TreeNode<V> brother = node.getBrother();
            TreeNode<V> parent = node.getParent();
            if (node.getRight() != null) {
                node.getRight().setColor(Color.BLACK);
                if (node.getLeft() != null) {
                    node.getRight().setLeft(node.getLeft());
                }
                parent.replace(node, node.getRight());
            } else if (node.getLeft() != null) {
                node.getLeft().setColor(Color.BLACK);
                parent.replace(node, node.getLeft());
            } else if (brother.getColor() == Color.RED) {
                parent.setColor(Color.RED);
                brother.setColor(Color.BLACK);
                if (brother.isRightOfParent()) {
                    rotateLeft(parent);
                } else {
                    rotateRight(parent);
                }
                deleteNode(node);
                return;
            } else {
                //brother color is black
                //parent color is black
                //node color is black
                if (brother.getRight() == null && brother.getLeft() == null) {
                    
                }
                if (brother.isRightOfParent()) {
                    if (brother.getRight() != null) {
                        brother.getRight().setColor(Color.BLACK);
                        if (brother.getLeft() != null) {
                            brother.getRight().setLeft(brother.getLeft());
                        }
                        rotateLeft(parent);
                    } else if (brother.getLeft() != null) {
                        brother.getLeft().setColor(Color.BLACK);
                        doubleRotateLeft(parent);
                    }
                } else {
                    //brother is on left of parent
                    if (brother.getLeft() != null) {
                        brother.getLeft().setColor(Color.BLACK);
                        if (brother.getRight() != null) {
                            brother.getLeft().setRight(brother.getRight());
                        }
                        rotateRight(parent);
                    } else if (brother.getRight() != null) {
                        brother.getRight().setColor(Color.BLACK);
                        doubleRotateRight(parent);
                    }
                }

            }
        }
    }


    @Override
    public boolean contain(V v) {
        return false;
    }


}

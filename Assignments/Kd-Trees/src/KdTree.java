import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;


public class KdTree {
    private Node root;
    private int N;

    private class Node {
        boolean vertical;
        Node left, right;
        Point2D point;

        private Node(Point2D p, boolean vertical) {
            point = p;
            this.vertical = vertical;
        }

        private int compare(Point2D p) {
            if (point.compareTo(p)==0) { return 0; }

            if (vertical) {
                if      (p.x() < point.x()) { return -1; }
                else if (p.x() > point.x()) { return  1; }
                else                        { return -1; }
            }
            else {
                if      (p.y() < point.y()) { return -1; }
                else if (p.y() > point.y()) { return  1; }
                else                        { return -1; }
            }
        }

/*        private int compare(RectHV rect) {
            if      (rect.contains(this.point))                                         { return  0; }
            else if ((x && rect.ymax() < point.y()) || (!x && rect.xmax() < point.x())) { return -1; }
            else if ((x && rect.ymin() > point.y()) || (!x && rect.xmin() > point.x())) { return  1; }
            else                                                                        { return  0; }
        }*/
    }

    public int size()        { return N;      }
    public boolean isEmpty() { return (N==0); }

    private Node insert(Node node, Point2D p, boolean vertical) {
        if (node==null) {
            this.N++;
            return new Node(p, vertical);
        }

        assert (node.vertical == vertical);
        int cmp = node.compare(p);
        if      (cmp < 0) { node.left  = insert(node.left,  p, !vertical); }
        else if (cmp > 0) { node.right = insert(node.right, p, !vertical); }

        return node;
    }

    public void insert(Point2D p) {
        root = insert(root, p, false);
    }

    public boolean contains(Point2D p) {
        Node node = root;

        while (node!=null) {
            int cmp = node.compare(p);
            if      (cmp < 0) { node = node.left;  }
            else if (cmp > 0) { node = node.right; }
            else              { return true;       }
        }

        return false;
    }

    private void draw(Node node) {
        if (node==null) return;

        this.draw(node.left);
        node.point.draw();
        this.draw(node.right);
    }

    public void draw() { this.draw(this.root); }

/*    private void range(ArrayList<Point2D> list, Node node, RectHV rect) {
        if (node==null) return;
        int cmp = node.compare(rect);

        if      (cmp > 0) { range(list, node.right, rect); }
        else if (cmp < 0) { range(list, node.left,  rect); }
        else {
            range(list, node.left, rect);
            list.add(node.point);
            range(list, node.right, rect);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect==null) { throw new IllegalArgumentException(); }

        ArrayList<Point2D> it = new ArrayList<>();
        this.range(it, this.root, rect);
        return it;
    }*/

    public Iterable<Point2D> range(RectHV rect) { return null; }

    public Point2D nearest(Point2D p) {
        return null;
    }

    public static void main(String[] args) {
        KdTree kdt = new KdTree();

        kdt.insert(new Point2D(1.0, 2.0));
        kdt.insert(new Point2D(15.0, 7.0));
        kdt.insert(new Point2D(7.0, 12.0));
        kdt.insert(new Point2D(4.0, 3.0));
        kdt.insert(new Point2D(3.0, 9.0));
        kdt.insert(new Point2D(-1.0, 20.0));

        System.out.println(kdt.size());
        System.out.println(kdt.contains(new Point2D(1.0, 2.0)));
        System.out.println(kdt.contains(new Point2D(14.0, 7.0)));
        System.out.println(kdt.contains(new Point2D(7.0, 12.0)));
        System.out.println(kdt.contains(new Point2D(4.2, 3.0)));
        System.out.println(kdt.contains(new Point2D(3.0, 9.0)));
        System.out.println(kdt.contains(new Point2D(-1.0, 20.0)));

        /*RectHV rect = new RectHV(-5, -2, 10, 10);
        System.out.println(kdt.range(rect));*/
    }
}

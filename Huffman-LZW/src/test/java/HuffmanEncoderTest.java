
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.datastructures.CustomPriorityQueue;
import huffmanlzw.huffman.HuffmanEncoder;
import huffmanlzw.datastructures.HuffmanTree;
import huffmanlzw.datastructures.Node;
import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mmatila
 */
public class HuffmanEncoderTest {

    File testFile;
    HuffmanEncoder encoder;
    String content;

    @Before
    public void setUp() {
        try {
            testFile = new File("testFile.txt");
            testFile.createNewFile();
            FileWriter writer = new FileWriter(testFile);
            writer.write("Hello world!");
            writer.close();
        } catch (Exception e) {

        }
        encoder = new HuffmanEncoder(testFile);
    }

    @Test
    public void contentIsReadCorrectlyIfFileExists() {
        encoder.contentToString();
        assertEquals(encoder.getContent(), "Hello world!");
    }

    @Test
    public void characterFrequenciesAreCountedCorrectly() {
        encoder.contentToString();
        encoder.generateFrequencies();
        CustomHashMap<Character, Integer> frequencies = encoder.getFrequencies();
        int h = frequencies.get('H');
        int e = frequencies.get('e');
        int l = frequencies.get('l');
        int o = frequencies.get('o');
        int excMark = frequencies.get('!');

        assertEquals(1, h);
        assertEquals(1, e);
        assertEquals(3, l);
        assertEquals(2, o);
        assertEquals(1, excMark);

    }

    @Test
    public void CustompriorityQueueGeneratesCorrectly() {
        CustomPriorityQueue queue = new CustomPriorityQueue();
        Node one = new Node('a', 3);
        Node two = new Node('b', 5);
        Node three = new Node('c', 2);
        Node four = new Node('d', 10);
        Node five = new Node('e', 1);

        queue.add(one);
        queue.add(two);
        queue.add(three);
        queue.add(four);
        queue.add(five);

        assertEquals(queue.poll(), five);
        assertEquals(queue.poll(), three);
        assertEquals(queue.poll(), one);
        assertEquals(queue.poll(), two);
        assertEquals(queue.poll(), four);
    }

    @Test
    public void huffmanTreeGeneratesCorrectly() {
        CustomPriorityQueue queue = new CustomPriorityQueue();
        Node one = new Node('a', 3);
        Node two = new Node('b', 5);
        Node three = new Node('c', 2);
        Node four = new Node('d', 10);
        Node five = new Node('e', 1);

        queue.add(one);
        queue.add(two);
        queue.add(three);
        queue.add(four);
        queue.add(five);

        HuffmanTree tree = new HuffmanTree(queue);
        tree.generate();
        Node root = tree.getRoot();

        assertEquals(21, root.getFrequency());
        assertEquals(11, root.getRight().getFrequency());
        assertEquals(10, root.getLeft().getFrequency());
    }

    @After
    public void deleteTestFile() {
        testFile.delete();
    }
}

package edu.iastate.cs311.hw4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

public class DiGraphTests {
    private static DiGraph<String> G;

    private static void testFromFile(String inFileName, String outFileName) throws IOException {
        FileInputStream inFileStream = new FileInputStream("testfiles/" + inFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inFileStream));
        String[] strLine = br.readLine().split("\\s+");
        int n = Integer.parseInt(strLine[0]);
        int m = Integer.parseInt(strLine[1]);
        G = new DiGraph<String>();
        for(int i=0; i<m; i++){
            strLine = br.readLine().split("\\s+");
            G.addEdge(strLine[0], strLine[1], Integer.parseInt(strLine[2]));
        }
        strLine = br.readLine().split("\\s+");
        String source = strLine[0], destination = strLine[1];
        HashMap<String, Integer> dist = DiGraph.Dijkstra(G, source, destination);

        FileInputStream outFileStream = new FileInputStream("testfiles/" + outFileName);
        br = new BufferedReader(new InputStreamReader(outFileStream));
        strLine = br.readLine().split("\\s+");
        int shortestDist = Integer.parseInt(strLine[0]);
        
        // separately checking the destination distance
        assertTrue("Failed for " + inFileName + ". The dist[destination] is wrong.", dist.get(destination) == shortestDist);
        
        strLine = br.readLine().split("\\s+");
        int[] expectedDist = new int[n];
        for(int i=0; i<n; i++) {
        	expectedDist[i] = Integer.parseInt(strLine[i]);
        	assertTrue("Failed for " + inFileName + ". The dist to node " + i + " is wrong.", dist.get(Integer.toString(i)) == expectedDist[i]);
        }
        
        verifyPath(G, source, destination, shortestDist, inFileName);
        
        br.close();
    }

    private static void verifyPath(DiGraph<String> G, String source, String destination, int shortestDist, String inFileName){
        LinkedStack<String> path = G.getPath();
        int dist = 0;
        String u = path.pop();
        assertEquals("Failed for " + inFileName + ". Starting node of the path is wrong.", u, source);
        HashSet<String> visited = new HashSet<>();
        visited.add(u);
        while(!path.isEmpty()){
            String v = path.pop();
            assertTrue("Failed for " + inFileName + ". The edge (" + u + ", " + v + ") does not exist." , G.hasEdge(u, v));
            assertTrue("Failed for " + inFileName + ". The node " + v + " is visited more than once.", !visited.contains(v));
            visited.add(v);
            u = v;
        }
        assertEquals("Failed for " + inFileName + ". The destination node of the path is wrong.", u, destination);
    }

    @Test
    public void test0() throws IOException {
        String inFileName = "input0.txt";
        String outFileName = "output0.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test1() throws IOException {
        String inFileName = "input1.txt";
        String outFileName = "output1.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test2() throws IOException {
        String inFileName = "input2.txt";
        String outFileName = "output2.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test3() throws IOException {
        String inFileName = "input3.txt";
        String outFileName = "output3.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test4() throws IOException {
        String inFileName = "input4.txt";
        String outFileName = "output4.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test5() throws IOException {
        String inFileName = "input5.txt";
        String outFileName = "output5.txt";
        testFromFile(inFileName, outFileName);
    }


    @Test
    public void test6() throws IOException {
        String inFileName = "input6.txt";
        String outFileName = "output6.txt";
        testFromFile(inFileName, outFileName);
    }
}
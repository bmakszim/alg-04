import java.io.*;
import java.util.*;

public class RoadConstruction {
    static class DSU {
        int[] parent;
        int[] size;
        int numComponents;
        int maxSize;

        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            numComponents = n;
            maxSize = 1;
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Find with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Union by size
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA != rootB) {
                numComponents--;
                if (size[rootA] < size[rootB]) {
                    parent[rootA] = rootB;
                    size[rootB] += size[rootA];
                    maxSize = Math.max(maxSize, size[rootB]);
                } else {
                    parent[rootB] = rootA;
                    size[rootA] += size[rootB];
                    maxSize = Math.max(maxSize, size[rootA]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Using BufferedReader for fast input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        DSU dsu = new DSU(n);

        // Using StringBuilder for fast output
        StringBuilder output = new StringBuilder();

        // Read and process each road construction
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            dsu.union(a, b);
            output.append(dsu.numComponents).append(" ").append(dsu.maxSize).append("\n");
        }

        // Output all results at once
        System.out.print(output.toString());
    }
}

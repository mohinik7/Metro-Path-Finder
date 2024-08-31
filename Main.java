package metropathfinder;

import java.util.*;

class MetroGraph {
    private int numStations; // Number of metro stations
    private List<List<Edge>> adjList; // Adjacency list to represent metro connections
    Map<String, Integer> stationMap; // Map station names to station IDs
    private String[] stationNames; // Store station names

    public MetroGraph(int numStations) {
        this.numStations = numStations;
        adjList = new ArrayList<>(numStations);
        for (int i = 0; i < numStations; i++) {
            adjList.add(new ArrayList<>());
        }
        stationMap = new HashMap<>();
        stationNames = new String[numStations];
    }

    public void addStation(String name, int id) {
        stationMap.put(name, id);
        stationNames[id] = name;
    }

    public void addEdge(int stationId1, int stationId2, int travelTime) {
        adjList.get(stationId1).add(new Edge(stationId2, travelTime));
        adjList.get(stationId2).add(new Edge(stationId1, travelTime)); // Bidirectional edge
    }

    public int[] findShortestPaths(int sourceStationId) {
        int[] shortestDistances = new int[numStations];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[sourceStationId] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        priorityQueue.offer(new Node(sourceStationId, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentStationId = currentNode.id;

            // Skip outdated distances
            if (currentNode.dist > shortestDistances[currentStationId])
                continue;

            for (Edge edge : adjList.get(currentStationId)) {
                int adjacentStationId = edge.to;
                int newDist = shortestDistances[currentStationId] + edge.travelTime;

                if (newDist < shortestDistances[adjacentStationId]) {
                    shortestDistances[adjacentStationId] = newDist;
                    priorityQueue.offer(new Node(adjacentStationId, newDist));
                }
            }
        }
        return shortestDistances;
    }

    // Public method to check if a station is valid
    public boolean isStationValid(String stationName) {
        return stationMap.containsKey(stationName);
    }

    static class Edge {
        int to, travelTime;

        public Edge(int to, int travelTime) {
            this.to = to;
            this.travelTime = travelTime;
        }
    }

    static class Node {
        int id, dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numStations = 22; // Number of metro stations
        MetroGraph metroGraph = new MetroGraph(numStations);

        // Add stations
        metroGraph.addStation("Central Secretariat", 0);
        metroGraph.addStation("Patel Chowk", 1);
        metroGraph.addStation("Rajiv Chowk", 2);
        metroGraph.addStation("Mandi House", 3);
        metroGraph.addStation("Supreme Court", 4);
        metroGraph.addStation("Indraprastha", 5);
        metroGraph.addStation("Yamuna Bank", 6);
        metroGraph.addStation("Akshardham", 7);
        metroGraph.addStation("Mayur Vihar", 8);
        metroGraph.addStation("Nizamuddin", 9);
        metroGraph.addStation("Ashram", 10);
        metroGraph.addStation("Vinobapuri", 11);
        metroGraph.addStation("Lajpat Nagar", 12);
        metroGraph.addStation("South Extention", 13);
        metroGraph.addStation("Dilli Haat", 14);
        metroGraph.addStation("Jor Bagh", 15);
        metroGraph.addStation("Lok Kalyan Marg", 16);
        metroGraph.addStation("Udyog Bhawan", 17);
        metroGraph.addStation("Khan Market", 18);
        metroGraph.addStation("JLN Stadium", 19);
        metroGraph.addStation("Jangpura", 20);
        metroGraph.addStation("Janpath", 21);

        // Add edges between metro stations with their respective travel times
        metroGraph.addEdge(0, 1, 2);
        metroGraph.addEdge(0, 21, 1);
        metroGraph.addEdge(0, 17, 2);
        metroGraph.addEdge(0, 18, 4);
        metroGraph.addEdge(1, 2, 2);
        metroGraph.addEdge(2, 3, 4);
        metroGraph.addEdge(3, 4, 2);
        metroGraph.addEdge(3, 21, 3);
        metroGraph.addEdge(4, 5, 2);
        metroGraph.addEdge(5, 6, 3);
        metroGraph.addEdge(6, 7, 3);
        metroGraph.addEdge(7, 8, 3);
        metroGraph.addEdge(8, 9, 5);
        metroGraph.addEdge(9, 10, 3);
        metroGraph.addEdge(10, 11, 3);
        metroGraph.addEdge(11, 12, 3);
        metroGraph.addEdge(12, 13, 3);
        metroGraph.addEdge(12, 20, 3);
        metroGraph.addEdge(13, 14, 2);
        metroGraph.addEdge(14, 15, 2);
        metroGraph.addEdge(15, 16, 2);
        metroGraph.addEdge(16, 17, 2);
        metroGraph.addEdge(18, 19, 3);
        metroGraph.addEdge(19, 20, 2);
        // Add other edges similarly...

        // Print station names
        System.out.println("1. Central Secretariat");
        System.out.println("2. Patel Chowk");
        System.out.println("3. Rajiv Chowk");
        System.out.println("4. Mandi House");
        System.out.println("5. Supreme Court");
        System.out.println("6. Indraprastha");
        System.out.println("7. Yamuna Bank");
        System.out.println("8. Akshardham");
        System.out.println("9. Mayur Vihar");
        System.out.println("10. Nizamuddin");
        System.out.println("11. Ashram");
        System.out.println("12. Vinobapuri");
        System.out.println("13. Lajpat Nagar");
        System.out.println("14. South Extention");
        System.out.println("15. Dilli Haat");
        System.out.println("16. Jor Bagh");
        System.out.println("17. Lok Kalyan Marg");
        System.out.println("18. Udyog Bhawan");
        System.out.println("19. Khan Market");
        System.out.println("20. JLN Stadium");
        System.out.println("21. Jangpura");
        System.out.println("22. Janpath");

        // Prompt user for starting station and destination
        System.out.println("Enter your starting station:");
        String startStation = scanner.nextLine().trim();
        System.out.println("Enter your destination station:");
        String destStation = scanner.nextLine().trim();

        // Check if station names are valid
        if (!metroGraph.isStationValid(startStation) || !metroGraph.isStationValid(destStation)) {
            System.out.println("Invalid station name. Please check the station names and try again.");
            return;
        }

        int startStationId = metroGraph.stationMap.get(startStation);
        int destStationId = metroGraph.stationMap.get(destStation);

        // Find the shortest distance between source and destination
        int[] shortestDistances = metroGraph.findShortestPaths(startStationId);
        int shortestDistance = shortestDistances[destStationId];

        if (shortestDistance == Integer.MAX_VALUE) {
            System.out.println("No path exists between " + startStation + " and " + destStation + ".");
        } else {
            System.out.println("Shortest time required to travel between " + startStation + " and " + destStation + " is " + shortestDistance + " mins.");
        }
    }
}


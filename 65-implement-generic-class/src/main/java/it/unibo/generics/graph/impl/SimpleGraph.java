package it.unibo.generics.graph.impl;

import java.util.*;
import it.unibo.generics.graph.api.Graph;

public class SimpleGraph<N> implements Graph<N>{

    private final Map<N, Set<N>> adjacentNodes;

    public SimpleGraph() {
        this.adjacentNodes = new HashMap<>();
    }

    public void addNode(final N node) {
        if (node != null && !this.nodeExists(node)) {
            this.adjacentNodes.put(node, new HashSet<>());
        }
    }

    private boolean nodeExists(final N node) {
        return this.adjacentNodes.get(node) != null;
    }

    public void addEdge(final N source, final N target) {
        if (source == null || target == null) {
            return;
        }
        
        this.adjacentNodes.get(source).add(target);
    }

    public Set<N> nodeSet() {
        return new HashSet<>(this.adjacentNodes.keySet());
    }

    public Set<N> linkedNodes(final N node) {
        return new HashSet<>(this.adjacentNodes.get(node));
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        final List<N> path = new ArrayList<>();
        final Map<N, N> pathsFromSource = this.bfs(source);
        N currentNode = target;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = pathsFromSource.get(currentNode);
        }

        Collections.reverse(path);
        return path;
    }


    private Map<N, N> bfs(N src) {

        final Map<N, N> predecessors = new HashMap<>();
        final Map<N, SimpleGraph.Status> nodeStatus = new HashMap<>();
        final List<N> queue = new ArrayList<>();

        for (final N node : this.adjacentNodes.keySet()) {
            nodeStatus.put(node, SimpleGraph.Status.NOT_FOUND);
            predecessors.put(node, null);
        }

        nodeStatus.put(src, SimpleGraph.Status.FOUND);
        queue.add(src);

        while (queue.size() != 0) {
            N currentNode = queue.remove(queue.size() - 1);
            for (final N n : this.adjacentNodes.get(currentNode)) {
                if (nodeStatus.get(n) == SimpleGraph.Status.NOT_FOUND) {
                    nodeStatus.put(n, SimpleGraph.Status.FOUND);
                    predecessors.put(n, currentNode);
                    queue.add(n);
                }
            }
            nodeStatus.put(currentNode, SimpleGraph.Status.EXPANDED);
        }
        
        return predecessors;
    }

    private static enum Status {
        NOT_FOUND,
        FOUND,
        EXPANDED
    }
}

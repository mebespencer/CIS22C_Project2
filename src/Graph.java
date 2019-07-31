import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    public LinkedList<String> idList;
    public ArrayList<LinkedList<Integer>> friendshipArray;

    Graph(int s) {
        idList = new LinkedList<>();
        friendshipArray = new ArrayList<>(s);
    }

    Graph() {
        idList = new LinkedList<>();
        friendshipArray = new ArrayList<>();
    }

    void befriend(Vertex v1, Vertex v2) {
        int id1 = getId(v1);
        int id2 = getId(v2);

        if (!friendshipArray.get(id1).contains(id2)) {
            friendshipArray.get(id1).add(id2);
            friendshipArray.get(id2).add(id1);
        }
    }

    int getId(Vertex v) {
        if (!idList.contains(v.name)) {
            return -1;
        } else {
            return idList.indexOf(v.name);
        }
    }

    Vertex getVertex(int id) {
        if (idList.get(id) == null) {
            System.out.println("The idList doesn't have an vertex associated with ID " + id);
            return null;
        } else {
            return new Vertex(idList.get(id));
        }

    }

    LinkedList<Vertex> mutualFriends(Vertex v1, Vertex v2) {
        int smallerVert;
        int largerVert;
        LinkedList<Vertex> mutuals = new LinkedList<>();

        if (friendshipArray.get(getId(v1)).size() <= friendshipArray.get(getId(v2)).size()) {
            smallerVert = getId(v1);
            largerVert = getId(v2);
        } else {
            smallerVert = getId(v2);
            largerVert = getId(v1);
        }

        for (int i = 0; i < friendshipArray.get(smallerVert).size(); i++) {
            if (friendshipArray.get(largerVert).contains(friendshipArray.get(smallerVert).get(i))) {
                mutuals.add(getVertex(friendshipArray.get(smallerVert).get(i)));
            }
        }
        return mutuals;
    }

    LinkedList<Vertex> recommendedFriends(Vertex v) {

        if(getId(v) == -1) {
            System.out.println("The name passed is not associated with a valid user. Try a different vertex.");
            return null;
        }


        LinkedList<Integer> immediateFriends = friendshipArray.get(getId(v));
        LinkedList<Integer> onceRemoved = new LinkedList<>();
        LinkedList<Integer> currentFriendsFriends;

        for (int i = 0; i < immediateFriends.size(); i++) {
            currentFriendsFriends = friendshipArray.get(immediateFriends.get(i));

            for (int j = 0; j < currentFriendsFriends.size(); j++) {
                if (!onceRemoved.contains(currentFriendsFriends.get(j)) && !immediateFriends.contains(currentFriendsFriends.get(j))) {
                    onceRemoved.add(currentFriendsFriends.get(j));
                }
            }
        }
        onceRemoved.remove(onceRemoved.indexOf(getId(v)));

        int currentMutualNum;
        LinkedList<Integer> topMutuals = new LinkedList<>();
        LinkedList<Vertex> returnList = new LinkedList<>();
        Vertex currentVertex;

        System.out.println("There are " + onceRemoved.size() + " potential friends for " + v.name + ".");
        System.out.println("The top 10 are as follows:");

        for (int k = 0; k < onceRemoved.size(); k++) {
            currentVertex = (getVertex(onceRemoved.get(k)));
            currentMutualNum = mutualFriends(currentVertex, v).size();
            if (k == 0) {
                topMutuals.addFirst(currentMutualNum);
                returnList.addFirst(currentVertex);
                continue;
            }
            for (int l = 0; l < topMutuals.size(); l++) {
                if (topMutuals.get(l) > currentMutualNum) {
                    if (l == 0) {
                        topMutuals.addFirst(currentMutualNum);
                        returnList.addFirst(currentVertex);
                        break;
                    } else {
                        topMutuals.add(l, currentMutualNum);
                        returnList.add(l, currentVertex);
                        break;
                    }

                } else if (l == topMutuals.size() - 1 && currentMutualNum >= topMutuals.get(l)) {
                    topMutuals.addLast(currentMutualNum);
                    returnList.addLast(currentVertex);
                    break;
                }

            }
        }


        while (topMutuals.size() > 10) {
            topMutuals.removeFirst();
            returnList.removeFirst();
        }
        for (int n = 0; n < topMutuals.size(); n++) {
            System.out.println(returnList.get(n).name + " with " + topMutuals.get(n) + " mutual friends.");
        }
        System.out.println("");


        return returnList;


    }

    LinkedList<Vertex> shortestChain(Vertex root, Vertex target) {
        int rootID = getId(root);
        int targetID = getId(target);
        boolean[] visited = new boolean[idList.size()];
        int[] visitedBy = new int[idList.size()];


        if(rootID == -1 || targetID == -1) {
            System.out.println("One of the names passed is not associated with a valid user. Try different vertices.");
            return null;
        }

        Queue<Integer> toVisit = new LinkedList<>();

        visited[rootID] = true;
        visitedBy[rootID] = -1;

        toVisit.add(rootID);
        int currentUserID;
        int currentFriendID;
        LinkedList<Integer> currentusersFriends;

        while ((!visited[targetID])) {
            currentUserID = toVisit.remove();
            currentusersFriends = friendshipArray.get(currentUserID);

            for(int i = 0; i < currentusersFriends.size(); i++) {
                currentFriendID = currentusersFriends.get(i);
                if(!visited[currentFriendID]) {
                    visited[currentFriendID] = true;
                    toVisit.add(currentFriendID);
                    visitedBy[currentFriendID] = currentUserID;
                }
            }
        }

        LinkedList<Vertex> friendChain = new LinkedList<>();
        int visitor;
        visitor = targetID;
        while(visitor != -1) {
            friendChain.add(getVertex(visitor));
            visitor = visitedBy[visitor];
        }

        System.out.println("The shortest friendship chain between " + root.name + " and " + target.name + " is:");
        for(Vertex vert : friendChain) {
            System.out.print(vert.name);
            if(vert != friendChain.getLast()) {
                System.out.print(" --> ");
            }
        }
        System.out.println("");
        System.out.println("");

        return friendChain;
    }
}

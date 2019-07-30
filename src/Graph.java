import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    public LinkedList<String> idList;
    public ArrayList<LinkedList<Integer>> friendshipArray;

    Graph(int s) {
        idList = new LinkedList<>();

        friendshipArray = new ArrayList<>(s);
       /* for (int i = 0; i < s; i++) {
            friendshipArray[i] = new LinkedList<>();
        } */
    }

    Graph() {
        idList = new LinkedList<>();

        friendshipArray = new ArrayList<>();
       /* for (int i = 0; i < s; i++) {
            friendshipArray[i] = new LinkedList<>();
        } */
    }

    void befriend(Vertex v1, Vertex v2) {
        int id1 = getId(v1);
        int id2 = getId(v2);

        if(!friendshipArray.get(id1).contains(id2)){
            friendshipArray.get(id1).add(id2);
            friendshipArray.get(id2).add(id1);
        } //else
            //System.out.println(v1.name + " and " + v2.name + " are already friends.");
    }

    int getId(Vertex v) {
        if(!idList.contains(v.name)) {
            //System.out.println("The idList doesn't have an ID associated with " + v.name);
            return -1;
        }
        else{
            return idList.indexOf(v.name);
        }
    }

    Vertex getVertex(int id){
        if(idList.get(id) == null){
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

        if(friendshipArray.get(getId(v1)).size() <= friendshipArray.get(getId(v2)).size()) {
            smallerVert = getId(v1);
            largerVert = getId(v2);
        } else {
            smallerVert = getId(v2);
            largerVert = getId(v1);
        }

        for(int i =0; i < friendshipArray.get(smallerVert).size(); i++) {
            if(friendshipArray.get(largerVert).contains(friendshipArray.get(smallerVert).get(i))) {
                mutuals.add(getVertex(friendshipArray.get(smallerVert).get(i)));
            }
        }
        return mutuals;
    }

    LinkedList<Vertex> recommendedFriends(Vertex v) {
        LinkedList<Integer> immediateFriends = friendshipArray.get(getId(v));
        LinkedList<Integer> onceRemoved = new LinkedList<>();
        LinkedList<Integer> currentFriendsFriends;

        for(int i = 0; i < immediateFriends.size(); i++) {
             currentFriendsFriends = friendshipArray.get(immediateFriends.get(i));

            for(int j = 0; j < currentFriendsFriends.size(); j++) {
                if(!onceRemoved.contains(currentFriendsFriends.get(j))){
                    onceRemoved.add(currentFriendsFriends.get(j));
                }
            }
        }

        int currentMutualNum;
        int[] topMutuals = new int[onceRemoved.size()];
        System.out.println("once removed size is " + onceRemoved.size());
        for(int k = 0; k < onceRemoved.size(); k++) {
            currentMutualNum = mutualFriends(getVertex(onceRemoved.get(k)), v).size();
            for(int l = 0; l < topMutuals.length; l++) {
               if(topMutuals[l] < currentMutualNum) {
                   if(l == topMutuals.length - 1){

                   }
               }
            }
        }


        for(int n = 0; n < topMutuals.length; n++) {
            System.out.println(topMutuals[n]);
        }

        return null;
    }

}

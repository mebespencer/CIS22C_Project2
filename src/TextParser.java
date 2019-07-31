import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class TextParser {

    static Graph constructGraphfromText() throws Exception {
       File file = new File("facebook_network.txt");
        Scanner sc = new Scanner(file);

        int linecount = 0;
        while (sc.hasNextLine()) {
            linecount++;
            sc.nextLine();
        }
        System.out.println("The Facebook Network file is " + linecount + " lines long.");
        System.out.println("Currently parsing it into an adjacency list... (this takes a while)");

        Graph constructedGraph = new Graph(linecount);

        sc = new Scanner(file);
        Vertex firstFriend;
        Vertex secondFriend;
        int stopper = linecount;

        while (sc.hasNextLine() && stopper != 0) {
            firstFriend = new Vertex(sc.next());
            secondFriend = new Vertex(sc.next());

            if(constructedGraph.getId(firstFriend) == -1) {
                constructedGraph.idList.add(firstFriend.name);
                constructedGraph.friendshipArray.add(new LinkedList<>());
            }
            if(constructedGraph.getId(secondFriend) == -1) {
                constructedGraph.idList.add(secondFriend.name);
                constructedGraph.friendshipArray.add(new LinkedList<>());
            }

            constructedGraph.befriend(firstFriend, secondFriend);
            stopper--;
        }
        System.out.println("Text parsing complete.");

        return constructedGraph;
    }

}

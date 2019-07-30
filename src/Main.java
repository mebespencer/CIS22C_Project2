import java.util.LinkedList;


public class Main {

    public static void main(String[] args)
    {
        Graph facebookGraph = new Graph();
       try{
           facebookGraph = TextParser.constructGraphfromText();
       }catch(Exception e){
           System.out.println("some error thrown");
       }
        Vertex testVertex = new Vertex("Lynch");
       facebookGraph.recommendedFriends(testVertex);

    }

}

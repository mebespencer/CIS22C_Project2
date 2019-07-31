public class Main {

    public static void main(String[] args)
    {
        Graph facebookGraph = new Graph();
       try{
           facebookGraph = TextParser.constructGraphfromText();
       }catch(Exception e){
           System.out.println("Unable to parse facebook_network.txt. Ensure that it's in the local files for this project.");
       }
        Vertex testVertex = new Vertex("Schmidt");
        Vertex testVertex2 = new Vertex("Jordan");


        facebookGraph.recommendedFriends(testVertex);

        facebookGraph.shortestChain(testVertex,testVertex2);

    }

}

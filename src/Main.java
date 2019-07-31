public class Main {

    public static void main(String[] args)
    {
        Graph facebookGraph = new Graph();
       try{
           facebookGraph = TextParser.constructGraphfromText();
       }catch(Exception e){
           System.out.println("Unable to parse facebook_network.txt. Ensure that it's in the local files for this project.");
           System.exit(1);
       }

       Vertex[][] pairsToTest = new Vertex[0][0];

       try{
           pairsToTest = TextParser.findFriendsfromText();
       }catch (Exception e) {
           System.out.println("Unable to parse find_friends.txt. Ensure that it's in the local files for this project.");
           System.exit(1);
       }

       Vertex firstUser;
       Vertex secondUser;

       for(int i =0; i < pairsToTest.length; i++) {
           firstUser = pairsToTest[i][0];
           secondUser = pairsToTest[i][1];
           facebookGraph.recommendedFriends(firstUser);
           facebookGraph.recommendedFriends(secondUser);
           facebookGraph.shortestChain(firstUser, secondUser);
       }

    }

}

import java.util.ArrayDeque;
public class LevelMostOccurrences {
    //Defines
    private static int ADD_LEVEL = 1;
    private static int MAX_NUM_LEVEL_ARRAY_SIZE = 2;
    private static int ROOT_LEVEL = 0;
    private static int NUM_NOT_IN_TREE = -1;

    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        BinNode<Integer> currentNode;
        int currentNodeLevel, previousNodeLevel = -1;
        int numOccurrences = 0;


        int[] maxNumOccurrences = new int[MAX_NUM_LEVEL_ARRAY_SIZE];
        //maxNumOccurrences[0] = NUM_NOT_IN_TREE; // set a default value for max_occurrences if num is not
                                                // in the binary tree.
        ArrayDeque<BinNode<Integer>> nodeQueue = new ArrayDeque<>();
        ArrayDeque<Integer> levelQueue = new ArrayDeque<>();
        nodeQueue.add(node);
        levelQueue.add(ROOT_LEVEL);

        while (!nodeQueue.isEmpty()) {
            currentNode = nodeQueue.remove();
            currentNodeLevel = levelQueue.remove();
            if (previousNodeLevel != currentNodeLevel) {
                if (maxNumOccurrences[1] < numOccurrences) {                     // 0 = MAX_LEVEL   1 = MAX_OCC
                    maxNumOccurrences[1] = numOccurrences;
                    maxNumOccurrences[0] = previousNodeLevel;
                    numOccurrences = 0; //reset
                }
            }
            if (num == currentNode.getData()) {
                numOccurrences++;
            }
            if (currentNode.getLeft() != null) {
                nodeQueue.add(currentNode.getLeft());
                levelQueue.add(currentNodeLevel + ADD_LEVEL);
            }

            if (currentNode.getRight() != null) {
                nodeQueue.add(currentNode.getRight());
                levelQueue.add(currentNodeLevel + ADD_LEVEL);
            }
            previousNodeLevel = currentNodeLevel;

        }
        if (maxNumOccurrences[1] < numOccurrences) {                     // 0 = MAX_LEVEL   1 = MAX_OCC
            maxNumOccurrences[1] = numOccurrences;
            maxNumOccurrences[0] = previousNodeLevel;
        } //last max check
        if (maxNumOccurrences[1] == 0){
            maxNumOccurrences[0] = NUM_NOT_IN_TREE;
        }
        return maxNumOccurrences[0]; //return a print of the level where the max occ of the num entered.
    }
}

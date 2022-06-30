import java.util.ArrayDeque;

/**
 * This class's purpose is to find levels in a binary tree.
 * Includes methods for calculating a level of certain numbers in a binary tree.
 */
public class LevelMostOccurrences {
    //Defines
    private static int ADD_LEVEL = 1;
    private static int MAX_NUM_LEVEL_ARRAY_SIZE = 2;
    private static int ROOT_LEVEL = 0;
    private static int NUM_NOT_IN_TREE = -1;

    /**
     * This method gets a root of a binary tree and a number and finds the level of the tree where the number is
     * most commonly seen.
     * @param node - The root or a value of a number in a binary tree.
     * @param num - An integer. This number doesnt have to be a number in the binary tree.
     * @return - A number indicating the level where "num" is most commonly seen in the given binary tree.
     * If "num" is not in the tree, returns "-1".
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        BinNode<Integer> currentNode;
        int currentNodeLevel, previousNodeLevel = -1;
        int numOccurrences = 0;

        int[] maxNumOccurrences = new int[MAX_NUM_LEVEL_ARRAY_SIZE];
        ArrayDeque<BinNode<Integer>> nodeQueue = new ArrayDeque<>(); //Initializing a queue for the values in the tree.
        ArrayDeque<Integer> levelQueue = new ArrayDeque<>(); //Initializing a queue for the levels of the values
        // in the tree.
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

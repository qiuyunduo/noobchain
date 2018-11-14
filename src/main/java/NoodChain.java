import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NoodChain
 * @Description TODO
 * @Author qiuyunduo
 * @Date 18-11-14 下午6:18e
 * @Version 1.0
 **/
public class NoodChain {
    public static int difficulty = 4;
    public static List<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {

        Block genesisBlock = new Block("0","this is the firstblock");
        System.out.println("Hash for Block1:"+genesisBlock.hash);
        blockchain.add(genesisBlock);
        blockchain.get(blockchain.size()-1).mineBlock(difficulty);

        Block secondBlock = new Block(blockchain.get(blockchain.size()-1).hash,"this is the secondblock");
        System.out.println("Hash for Block2:"+secondBlock.hash);
        blockchain.add(secondBlock);
        blockchain.get(blockchain.size()-1).mineBlock(difficulty);

        Block thirdBlock = new Block(blockchain.get(blockchain.size()-1).hash,"this is the thirdblock");
        System.out.println("Hash for Block3:"+thirdBlock.hash);
        blockchain.add(thirdBlock);
        blockchain.get(blockchain.size()-1).mineBlock(difficulty);

        String blockchaninJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);


        System.out.println(blockchaninJson);
    }

    public static Boolean isChainVaild(){
        Block currentBlock;
        Block previousBlock;

        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            String hashTargret = new String(new char[difficulty]).replace('\0','0');

            if(!currentBlock.previousHash.equals(currentBlock.calculateHash())){
                System.err.println("currentHashes not equal");
                return false;
            }

            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.err.println("previousHash not equal");
                return false;
            }

            if(!currentBlock.hash.substring(0,difficulty).equals(hashTargret)){
                System.err.println("this block hasn't been mined");
            }
        }

        return true;
    }
}

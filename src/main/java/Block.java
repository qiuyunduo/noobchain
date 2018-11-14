import java.util.Date;

/**
 * @ClassName Block
 * @Description TODO
 * @Author qiuyunduo
 * @Date 18-11-14 下午5:39
 * @Version 1.0
 **/
public class Block {
    public String hash;

    public String previousHash;

    private String data;

    private long timeStamp;

    private int nonce;

    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //构造该区块的哈希值
    public String calculateHash(){
        String calculatehash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data);
        return calculatehash;
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0','0');

        while(!hash.substring(0,difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Block Mine!!!: "+hash);
    }
}

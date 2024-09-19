public class GachaVillain {
    String name;
    String rarity;
    int Hp;
    int Attack;
    int Defense;
    int Speed;

    public String getName() {
        return name;
    }
    public String getRarity() {
        return rarity;
    }
    public int getHp() {
        return Hp;
    }
    public int getAttack() {
        return Attack;
    }
    public int getDefense() {
        return Defense;
    }
    public int getSpeed() {
        return Speed;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public void setHp(int Hp) {
        this.Hp = Hp;
    }
    public void setAttack(int attack) {
        this.Attack = attack;
    }
    public void setDefense(int defense) {
        this.Defense = defense;
    }
    public void setSpeed(int speed) {
        this.Speed = speed;
    }

    public int[] getImportantStats(){
        return new int[]{this.rarity.charAt(0)-'0',this.Attack,this.Defense,this.Speed,3};
    }

    public GachaVillain(String name,String rarity,int Hp,int attack,int defense,int speed){
        this.name = name;
        this.rarity = rarity;
        this.Hp = Hp;
        this.Attack = attack;
        this.Defense = defense;
        this.Speed = speed;
    }

    public void printGachaVillain(){
        System.out.println("Name: " +name+" rarity: "+this.rarity+" Hp: "+this.Hp+" Attack: "+this.Attack+" Defense: "+this.Defense+" Speed: "+this.Speed);
    }
}

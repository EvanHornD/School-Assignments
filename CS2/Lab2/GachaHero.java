public class GachaHero {
    String name;
    String rarity;
    int Hp;
    int Attack;
    int Defense;
    int Speed;
    int Mp;
    int Luck;

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
    public int getMp() {
        return Mp;
    }
    public int getLuck() {
        return Luck;
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
    public void setMp(int Mp) {
        this.Mp = Mp;
    }
    public void setLuck(int luck) {
        this.Luck = luck;
    }

    public int[] getImportantStats(){
        return new int[]{this.rarity.charAt(0)-'0',this.Attack,this.Defense,this.Speed,this.Luck};
    }

    public GachaHero(String name,String rarity,int Hp,int attack,int defense,int speed,int Mp,int luck){
        this.name = name;
        this.rarity = rarity;
        this.Hp = Hp;
        this.Attack = attack;
        this.Defense = defense;
        this.Speed = speed;
        this.Mp = Mp;
        this.Luck = luck;
    }

    public void printGachaHero(){
        System.out.println("Name: " +name+" rarity: "+this.rarity+" Hp: "+this.Hp+" Attack: "+this.Attack+" Defense: "+this.Defense+" Speed: "+this.Speed+" Mp: "+this.Mp+" Luck: "+this.Luck);
    }
}
